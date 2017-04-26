package com.example.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * ProjectName：IcexOne
 * Describe：学习模块每日推荐bean
 * Author：Icex
 * CreationTime：2017/2/23
 */

public class DailyBean implements Serializable{

    private long version;
    private List<DataList> data;

    public void setVersion(long version) {
        this.version = version;
    }

    public void setData(List<DataList> data) {
        this.data = data;
    }

    public long getVersion() {
        return version;
    }

    public List<DataList> getData() {
        return data;
    }

    public static class DataList {
        private int id;
        private String name;
        private String desc;
        private int style;
        private int isNameDisplay;
        private ActionEntity action;
        private List<DataEntity> data;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setStyle(int style) {
            this.style = style;
        }

        public void setIsNameDisplay(int isNameDisplay) {
            this.isNameDisplay = isNameDisplay;
        }

        public void setAction(ActionEntity action) {
            this.action = action;
        }

        public void setData(List<DataEntity> data) {
            this.data = data;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

        public int getStyle() {
            return style;
        }

        public int getIsNameDisplay() {
            return isNameDisplay;
        }

        public ActionEntity getAction() {
            return action;
        }

        public List<DataEntity> getData() {
            return data;
        }

        public static class ActionEntity {

            private int type;

            public void setType(int type) {
                this.type = type;
            }

            public int getType() {
                return type;
            }
        }

        public static class DataEntity {

            private int id;
            private String name;
            private String desc;
            private String picUrl;
            private ActionEntity action;
            private AlbumRightKeyEntity albumRightKey;
            private int status;
            private int isExclusive;

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public void setAction(ActionEntity action) {
                this.action = action;
            }

            public void setAlbumRightKey(AlbumRightKeyEntity albumRightKey) {
                this.albumRightKey = albumRightKey;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setIsExclusive(int isExclusive) {
                this.isExclusive = isExclusive;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getDesc() {
                return desc;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public ActionEntity getAction() {
                return action;
            }

            public AlbumRightKeyEntity getAlbumRightKey() {
                return albumRightKey;
            }

            public int getStatus() {
                return status;
            }

            public int getIsExclusive() {
                return isExclusive;
            }

            public static class ActionEntity {

                private int type;
                private String value;

                public void setType(int type) {
                    this.type = type;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public int getType() {
                    return type;
                }

                public String getValue() {
                    return value;
                }
            }

            public static class AlbumRightKeyEntity {

                private boolean buyFlag;
                private int price;
                private Object vipFree;
                private Object quality;
                private Object active;
                private int count;
                private boolean buy;
                private String dmsg;

                public void setBuyFlag(boolean buyFlag) {
                    this.buyFlag = buyFlag;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public void setVipFree(Object vipFree) {
                    this.vipFree = vipFree;
                }

                public void setQuality(Object quality) {
                    this.quality = quality;
                }

                public void setActive(Object active) {
                    this.active = active;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public void setBuy(boolean buy) {
                    this.buy = buy;
                }

                public void setDmsg(String dmsg) {
                    this.dmsg = dmsg;
                }

                public boolean getBuyFlag() {
                    return buyFlag;
                }

                public int getPrice() {
                    return price;
                }

                public Object getVipFree() {
                    return vipFree;
                }

                public Object getQuality() {
                    return quality;
                }

                public Object getActive() {
                    return active;
                }

                public int getCount() {
                    return count;
                }

                public boolean getBuy() {
                    return buy;
                }

                public String getDmsg() {
                    return dmsg;
                }
            }
        }
    }
}
