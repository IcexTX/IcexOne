package com.example.library.ganHuo;

import com.example.library.httpRequest.ParamNames;

import java.io.Serializable;
import java.util.List;

/**
 * ProjectName：IcexOne
 * Describe：每日推荐bean
 * Author：Icex
 * CreationTime：2017/3/2
 */

public class DailyContentBean implements Serializable {

    @ParamNames("error")
    private boolean error;
    //返回结果集
    @ParamNames("results")
    private ResultsEntity results;
    //返回结果标题集
    @ParamNames("category")
    private List<String> category;

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(ResultsEntity results) {
        this.results = results;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public boolean getError() {
        return error;
    }

    public ResultsEntity getResults() {
        return results;
    }

    public List<String> getCategory() {
        return category;
    }

    public static class ResultsEntity {

        @ParamNames("Android")
        private List<GeneralContent> Android;
        @ParamNames("iOS")
        private List<GeneralContent> iOS;
        @ParamNames("休息视频")
        private List<GeneralContent> video;
        @ParamNames("前端")
        private List<GeneralContent> web;
        @ParamNames("瞎推荐")
        private List<GeneralContent> recommended;
        @ParamNames("福利")
        private List<GeneralContent> welfare;
        @ParamNames("拓展资源")
        private List<GeneralContent> resources;
        @ParamNames("App")
        private List<GeneralContent> app;

        public List<GeneralContent> getAndroid() {
            return Android;
        }

        public void setAndroid(List<GeneralContent> android) {
            Android = android;
        }

        public List<GeneralContent> getiOS() {
            return iOS;
        }

        public void setiOS(List<GeneralContent> iOS) {
            this.iOS = iOS;
        }

        public List<GeneralContent> getVideo() {
            return video;
        }

        public void setVideo(List<GeneralContent> video) {
            this.video = video;
        }

        public List<GeneralContent> getWeb() {
            return web;
        }

        public void setWeb(List<GeneralContent> web) {
            this.web = web;
        }

        public List<GeneralContent> getRecommended() {
            return recommended;
        }

        public void setRecommended(List<GeneralContent> recommended) {
            this.recommended = recommended;
        }

        public List<GeneralContent> getWelfare() {
            return welfare;
        }

        public void setWelfare(List<GeneralContent> welfare) {
            this.welfare = welfare;
        }

        public List<GeneralContent> getResources() {
            return resources;
        }

        public void setResources(List<GeneralContent> resources) {
            this.resources = resources;
        }

        public List<GeneralContent> getApp() {
            return app;
        }

        public void setApp(List<GeneralContent> app) {
            this.app = app;
        }
    }
}
