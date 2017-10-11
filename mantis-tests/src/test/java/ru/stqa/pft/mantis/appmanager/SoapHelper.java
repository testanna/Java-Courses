package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by User on 009 09.10.17.
 */
public class SoapHelper {
    private ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    public MantisConnectPortType getMantisConnect() throws MalformedURLException, ServiceException {
        return (MantisConnectPortType) new MantisConnectLocator()
                .getMantisConnectPort(new URL("http://localhost/mantisbt-2.6.0/api/soap/mantisconnect.php"));
    }

    public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible
                (app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        return Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
                .collect(Collectors.toSet());
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories(app.getProperty("web.adminLogin"),
                app.getProperty("web.adminPassword"), BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()),
                issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"),
                issueData);
        IssueData createdIssueData = mc.mc_issue_get(app.getProperty("web.adminLogin"),
                app.getProperty("web.adminPassword"), issueId);

        return new Issue().withId(createdIssueData.getId().intValue())
                .withSummary(createdIssueData.getSummary())
                .withDescription(createdIssueData.getDescription())
                .withProject(new Project().withName(createdIssueData.getProject().getName())
                        .withId(createdIssueData.getProject().getId().intValue()));
    }

    public String GetIssueState(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData issueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"),
                BigInteger.valueOf(issueId));
        ObjectRef issueStatus = issueData.getStatus();
        String statusName = issueStatus.getName();
        return statusName;
    }
}
