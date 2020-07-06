package com.company.Pojos;

import java.util.List;

public class Courses {
    // these 3 nodes return Arrays inside Courses Json
    private List<WebAutomation> webAutomation;
    private List<Api> api;
    private List<Mobile> mobile;

    public List<WebAutomation> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<WebAutomation> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List<Api> getApi() {
        return api;
    }

    public void setApiList(List<Api> apiList) {
        this.api = apiList;
    }

    public List<Mobile> getMobile() {
        return mobile;
    }

    public void setMobile(List<Mobile> mobile) {
        this.mobile = mobile;
    }
}
