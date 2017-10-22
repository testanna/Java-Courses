package ru.stqa.pft.rest;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {

    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor()
                .execute(Request.Get("http://demo.bugify.com/api/issues.json?page=30&limit=300"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }


    public int createIssue(Issue issue) throws IOException {
        String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", issue.getDescription()),
                        new BasicNameValuePair("description", issue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private Executor getExecutor(){
        return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");
    }

    private boolean isIssueOpen(int issueId) throws IOException {
        String json = getExecutor()
                .execute(Request.Get("http://demo.bugify.com/api/issues/" + Integer.toString(issueId) +
                        ".json?attachments=false&comments=false&followers=false&history=false"))
                .returnContent().asString();
        JsonObject parsed = (JsonObject) new JsonParser().parse(json);
        JsonArray jarray = parsed.getAsJsonArray("issues");
        parsed = jarray.get(0).getAsJsonObject();
        String issueState1 = parsed.get("state_name").toString();
        String issueState = issueState1.substring(1, issueState1.length() -1 );
        ///String issueState = issueStateJ.getAsString();

        if (issueState.equals("Open")) {
            return true;
        } else {
            return false;
        }
    }


}
