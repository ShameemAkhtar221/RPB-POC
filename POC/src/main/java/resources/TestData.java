package resources;

import API.Pojo.*;

import java.util.ArrayList;
import java.util.List;

public class TestData {

//    Webdriver testing data
    public static String employeeName = "Shameem Akhtar";
    public static int explicitWait = 30;
    public static String infoSummery = "Highly motivated and detail-oriented Automation Engineer with 2 years of experience in designing, developing and implementing automation solutions. Proficient in PLC programming, HMI design, and SCADA systems. Skilled in troubleshooting and providing technical support to ensure smooth operations of automated machinery. Proven ability to manage projects from conception to completion and collaborate effectively with cross-functional teams.";
    public static String jobDescription = "Job Summary:\n" +
            "We are seeking a motivated and detail-oriented Automation Engineer with 2 years of experience to join our team. The Automation Engineer will be responsible for designing, developing, and implementing automation solutions for our manufacturing processes. The successful candidate will have experience in PLC programming, HMI design, and SCADA systems.";
    public static int speedLimit = 0;


//    ResAssured Api testing data

    public static String getEmployeeName = "Shameem";


    public static void profileBody(ProfilePutRequest requestBody) {

        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setCompanyName("ABC Company");
        basicInfo.setDesignation("642a8399a849bb8e2c66bf17");
        basicInfo.setEmail("john@example.com");
        basicInfo.setPhone("1234567890");
        basicInfo.setGitLink("https://github.com/john");
        basicInfo.setLinkedLink("https://linkedin.com/in/john-doe");
        basicInfo.setBasicInfoSummary("Experienced software engineer with expertise in Java development.");

        List<Experience> experiences = new ArrayList<>();
        Experience experiencePast = new Experience();

        //current experience
        experiencePast.setCompanyName("Synavos Solutions");
        experiencePast.setStartsFrom("2023-01-01");
        experiencePast.setEndsTo("2023-01-01");
        experiencePast.setDesignation("Sr. SQA Engineer");
        experiencePast.setJobDescription("Developed and maintained web applications using Java and Spring framework.");
        experiencePast.setExperienceType("current");
        experiences.add(experiencePast);

        // old experience
        Experience experienceCurrent = new Experience();
        experienceCurrent.setCompanyName("Nisum");
        experienceCurrent.setStartsFrom("2018-01-01");
        experienceCurrent.setEndsTo("2021-12-31");
        experienceCurrent.setDesignation("Sr. SQA Engineer");
        experienceCurrent.setJobDescription("Developed and maintained web applications using Java and Spring framework.");
        experienceCurrent.setExperienceType("past");
        experiences.add(experienceCurrent);

        List<Skill> skills = new ArrayList<>();

        Skill skill1 = new Skill();
        skill1.setSkillName("Java");
        skill1.setSkillType("TECHNICAL");
        skills.add(skill1);

        Skill skill2 = new Skill();
        skill2.setSkillName("Selenium WebDriver");
        skill2.setSkillType("TECHNICAL");
        skills.add(skill2);

        Skill skill3 = new Skill();
        skill3.setSkillName("Rest-Assured library");
        skill3.setSkillType("TECHNICAL");
        skills.add(skill3);


        List<Certificate> certificates = new ArrayList<>();
        Certificate certificate = new Certificate();
        certificate.setCertName("Java Certification");
        certificate.setCertInstitute("Oracle");
        certificate.setCertStartsFrom("2022-01-01");
        certificate.setCertEndsTo("2022-12-31");
        certificates.add(certificate);

        List<Academic> academics = new ArrayList<>();
        Academic academic = new Academic();
        academic.setAcademicDegreeName("Bachelor of Science in Computer Science");
        academic.setAcademicInstitute("University XYZ");
        academic.setAcademicStartsFrom("2014-09-01");
        academic.setAcademicEndsTo("2018-06-30");
        academics.add(academic);

        List<Award> awards = new ArrayList<>();
        Award award = new Award();
        award.setAwardTitle("Best Employee of the Year");
        award.setAwardDescription("Recognized for outstanding performance and dedication to the team.");
        awards.add(award);

        List<Project> projects = new ArrayList<>();
        Project project = new Project();
        project.setProjectName("E-commerce Website");
        project.setProjectType("Web Application");
        project.setProjectTechStack("Java, Spring Boot, MySQL");
        project.setProjectLink("https://example.com");
        project.setProjectImage("https://api-qa.poc.synavos.com/uploads/a0ed41cd-e8b4-4599-8c0e-6d4dd90b5297.png");
        projects.add(project);

        requestBody.setBasicInfo(basicInfo);
        requestBody.setExperiences(experiences);
        requestBody.setSkills(skills);
        requestBody.setCertificates(certificates);
        requestBody.setAcademics(academics);
        requestBody.setAwardsAndHonors(awards);
        requestBody.setProjects(projects);

    }

}
