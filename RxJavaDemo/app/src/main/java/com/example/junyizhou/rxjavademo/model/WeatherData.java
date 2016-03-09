package com.example.junyizhou.rxjavademo.model;


/**
 * Created by JunyiZhou on 2016/3/2.
 */
public class WeatherData {

    /**
     * imgurl : http://img01.cheyipai.com/ad/101.png
     * nowTmp : 6
     * cond : 多云
     * qlty : 重度
     * maxTmp : 17
     * minTmp : 2
     * windLevel : 微风
     * city : 北京
     */

    private DataEntity data;
    /**
     * data : {"imgurl":"http://img01.cheyipai.com/ad/101.png","nowTmp":"6","cond":"多云","qlty":"重度","maxTmp":"17","minTmp":"2","windLevel":"微风","city":"北京"}
     * resCode : 0
     * msg : 操作成功
     */

    private int resCode;
    private String msg;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataEntity getData() {
        return data;
    }

    public int getResCode() {
        return resCode;
    }

    public String getMsg() {
        return msg;
    }

    public static class DataEntity {
        private String imgurl;
        private String nowTmp;
        private String cond;
        private String qlty;
        private String maxTmp;
        private String minTmp;
        private String windLevel;
        private String city;

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public void setNowTmp(String nowTmp) {
            this.nowTmp = nowTmp;
        }

        public void setCond(String cond) {
            this.cond = cond;
        }

        public void setQlty(String qlty) {
            this.qlty = qlty;
        }

        public void setMaxTmp(String maxTmp) {
            this.maxTmp = maxTmp;
        }

        public void setMinTmp(String minTmp) {
            this.minTmp = minTmp;
        }

        public void setWindLevel(String windLevel) {
            this.windLevel = windLevel;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getImgurl() {
            return imgurl;
        }

        public String getNowTmp() {
            return nowTmp;
        }

        public String getCond() {
            return cond;
        }

        public String getQlty() {
            return qlty;
        }

        public String getMaxTmp() {
            return maxTmp;
        }

        public String getMinTmp() {
            return minTmp;
        }

        public String getWindLevel() {
            return windLevel;
        }

        public String getCity() {
            return city;
        }
    }
}
