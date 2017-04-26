package com.example.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * ProjectName：IcexOne
 * Describe：图片轮播bean
 * Author：Icex
 * CreationTime：2017/3/3
 */

public class BannerBean implements Serializable {

    private int error;
    private List<DataEntity> data;

    public void setError(int error) {
        this.error = error;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getError() {
        return error;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {

        private int id;
        private String title;
        private String pic_url;
        private String tv_pic_url;
        private RoomEntity room;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public void setTv_pic_url(String tv_pic_url) {
            this.tv_pic_url = tv_pic_url;
        }

        public void setRoom(RoomEntity room) {
            this.room = room;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getPic_url() {
            return pic_url;
        }

        public String getTv_pic_url() {
            return tv_pic_url;
        }

        public RoomEntity getRoom() {
            return room;
        }

        public static class RoomEntity {

            private String room_id;
            private String room_src;
            private String vertical_src;
            private int isVertical;
            private String cate_id;
            private String room_name;
            private String vod_quality;
            private String show_status;
            private String show_time;
            private String owner_uid;
            private String specific_catalog;
            private String specific_status;
            private String credit_illegal;
            private String is_white_list;
            private String cur_credit;
            private String low_credit;
            private int online;
            private String nickname;
            private String url;
            private String game_url;
            private String game_name;
            private String game_icon_url;
            private String show_details;
            private String owner_avatar;
            private int is_pass_player;
            private int open_full_screen;
            private String owner_weight;
            private String fans;
            private int is_high_game;
            private String column_id;
            private CateLimitEntity cate_limit;
            private List<CdnsWithNameEntity> cdnsWithName;

            public void setRoom_id(String room_id) {
                this.room_id = room_id;
            }

            public void setRoom_src(String room_src) {
                this.room_src = room_src;
            }

            public void setVertical_src(String vertical_src) {
                this.vertical_src = vertical_src;
            }

            public void setIsVertical(int isVertical) {
                this.isVertical = isVertical;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public void setRoom_name(String room_name) {
                this.room_name = room_name;
            }

            public void setVod_quality(String vod_quality) {
                this.vod_quality = vod_quality;
            }

            public void setShow_status(String show_status) {
                this.show_status = show_status;
            }

            public void setShow_time(String show_time) {
                this.show_time = show_time;
            }

            public void setOwner_uid(String owner_uid) {
                this.owner_uid = owner_uid;
            }

            public void setSpecific_catalog(String specific_catalog) {
                this.specific_catalog = specific_catalog;
            }

            public void setSpecific_status(String specific_status) {
                this.specific_status = specific_status;
            }

            public void setCredit_illegal(String credit_illegal) {
                this.credit_illegal = credit_illegal;
            }

            public void setIs_white_list(String is_white_list) {
                this.is_white_list = is_white_list;
            }

            public void setCur_credit(String cur_credit) {
                this.cur_credit = cur_credit;
            }

            public void setLow_credit(String low_credit) {
                this.low_credit = low_credit;
            }

            public void setOnline(int online) {
                this.online = online;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setGame_url(String game_url) {
                this.game_url = game_url;
            }

            public void setGame_name(String game_name) {
                this.game_name = game_name;
            }

            public void setGame_icon_url(String game_icon_url) {
                this.game_icon_url = game_icon_url;
            }

            public void setShow_details(String show_details) {
                this.show_details = show_details;
            }

            public void setOwner_avatar(String owner_avatar) {
                this.owner_avatar = owner_avatar;
            }

            public void setIs_pass_player(int is_pass_player) {
                this.is_pass_player = is_pass_player;
            }

            public void setOpen_full_screen(int open_full_screen) {
                this.open_full_screen = open_full_screen;
            }

            public void setOwner_weight(String owner_weight) {
                this.owner_weight = owner_weight;
            }

            public void setFans(String fans) {
                this.fans = fans;
            }

            public void setIs_high_game(int is_high_game) {
                this.is_high_game = is_high_game;
            }

            public void setColumn_id(String column_id) {
                this.column_id = column_id;
            }

            public void setCate_limit(CateLimitEntity cate_limit) {
                this.cate_limit = cate_limit;
            }

            public void setCdnsWithName(List<CdnsWithNameEntity> cdnsWithName) {
                this.cdnsWithName = cdnsWithName;
            }

            public String getRoom_id() {
                return room_id;
            }

            public String getRoom_src() {
                return room_src;
            }

            public String getVertical_src() {
                return vertical_src;
            }

            public int getIsVertical() {
                return isVertical;
            }

            public String getCate_id() {
                return cate_id;
            }

            public String getRoom_name() {
                return room_name;
            }

            public String getVod_quality() {
                return vod_quality;
            }

            public String getShow_status() {
                return show_status;
            }

            public String getShow_time() {
                return show_time;
            }

            public String getOwner_uid() {
                return owner_uid;
            }

            public String getSpecific_catalog() {
                return specific_catalog;
            }

            public String getSpecific_status() {
                return specific_status;
            }

            public String getCredit_illegal() {
                return credit_illegal;
            }

            public String getIs_white_list() {
                return is_white_list;
            }

            public String getCur_credit() {
                return cur_credit;
            }

            public String getLow_credit() {
                return low_credit;
            }

            public int getOnline() {
                return online;
            }

            public String getNickname() {
                return nickname;
            }

            public String getUrl() {
                return url;
            }

            public String getGame_url() {
                return game_url;
            }

            public String getGame_name() {
                return game_name;
            }

            public String getGame_icon_url() {
                return game_icon_url;
            }

            public String getShow_details() {
                return show_details;
            }

            public String getOwner_avatar() {
                return owner_avatar;
            }

            public int getIs_pass_player() {
                return is_pass_player;
            }

            public int getOpen_full_screen() {
                return open_full_screen;
            }

            public String getOwner_weight() {
                return owner_weight;
            }

            public String getFans() {
                return fans;
            }

            public int getIs_high_game() {
                return is_high_game;
            }

            public String getColumn_id() {
                return column_id;
            }

            public CateLimitEntity getCate_limit() {
                return cate_limit;
            }

            public List<CdnsWithNameEntity> getCdnsWithName() {
                return cdnsWithName;
            }

            public static class CateLimitEntity {
                /**
                 * limit_type : 0
                 * limit_num : 0
                 * limit_threshold : 0
                 * limit_time : 30
                 */

                private int limit_type;
                private int limit_num;
                private int limit_threshold;
                private int limit_time;

                public void setLimit_type(int limit_type) {
                    this.limit_type = limit_type;
                }

                public void setLimit_num(int limit_num) {
                    this.limit_num = limit_num;
                }

                public void setLimit_threshold(int limit_threshold) {
                    this.limit_threshold = limit_threshold;
                }

                public void setLimit_time(int limit_time) {
                    this.limit_time = limit_time;
                }

                public int getLimit_type() {
                    return limit_type;
                }

                public int getLimit_num() {
                    return limit_num;
                }

                public int getLimit_threshold() {
                    return limit_threshold;
                }

                public int getLimit_time() {
                    return limit_time;
                }
            }

            public static class CdnsWithNameEntity {
                /**
                 * name : 主线路
                 * cdn : ws
                 */

                private String name;
                private String cdn;

                public void setName(String name) {
                    this.name = name;
                }

                public void setCdn(String cdn) {
                    this.cdn = cdn;
                }

                public String getName() {
                    return name;
                }

                public String getCdn() {
                    return cdn;
                }
            }
        }
    }
}
