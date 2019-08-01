package com.menuz.ui;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class GalleryModel  {


    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * success : false
         * gallery : [{"groupId":"2","groupName":"Breakfast","groupParentId":"0","groupImage":"Breakfast.png","subgroups":[],"items":[{"itemId":"841","itemName":"Chachuka","itemGroupId":"2","itemPrice":"65","itemImage":"shakshuka.jpg"},{"itemId":"65","itemName":"Couple Morning","itemGroupId":"2","itemPrice":"116","itemImage":"couplebreakfast.jpg"},{"itemId":"66","itemName":"Couple Omlet","itemGroupId":"2","itemPrice":"124","itemImage":"couplebreakfast.jpg"},{"itemId":"85","itemName":"Italian Toast","itemGroupId":"2","itemPrice":"49","itemImage":"toast.jpg"}]},{"groupId":"132","groupName":"Salads","groupParentId":"0","groupImage":"SaladIcon.png","subgroups":[],"items":[{"itemId":"143","itemName":"סלט טונה ","itemGroupId":"132","itemPrice":"58","itemImage":""},{"itemId":"148","itemName":"סלט ישראלי ","itemGroupId":"132","itemPrice":"55","itemImage":""}]},{"groupId":"14","groupName":"Fishes","groupParentId":"0","groupImage":"fish.png","subgroups":[],"items":[{"itemId":"120","itemName":"Dennis","itemGroupId":"14","itemPrice":"88","itemImage":"denis.jpg"},{"itemId":"130","itemName":"Fish & Chips","itemGroupId":"14","itemPrice":"74","itemImage":"FishNchips.jpg"},{"itemId":"121","itemName":"Fish hamburger","itemGroupId":"14","itemPrice":"69","itemImage":"fishHamburger.jpg"},{"itemId":"279","itemName":"Oriental Kebab","itemGroupId":"14","itemPrice":"69","itemImage":"kebab.jpg"},{"itemId":"118","itemName":"Salmon fillet","itemGroupId":"14","itemPrice":"84","itemImage":"salmon.jpg"}]},{"groupId":"4","groupName":"Cold Drinks","groupParentId":"0","groupImage":"hotdrinksicon.png","subgroups":[],"items":[]},{"groupId":"3","groupName":"Hot Drinks","groupParentId":"0","groupImage":"hotdrinksicon.png","subgroups":[],"items":[{"itemId":"21","itemName":"Americano","itemGroupId":"3","itemPrice":"13","itemImage":"Espresso.jpg"}]}]
         */

        private String success;
        private List<GalleryBean> gallery;

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public List<GalleryBean> getGallery() {
            return gallery;
        }

        public void setGallery(List<GalleryBean> gallery) {
            this.gallery = gallery;
        }

        public static class GalleryBean implements Parcelable {
            /**
             * groupId : 2
             * groupName : Breakfast
             * groupParentId : 0
             * groupImage : Breakfast.png
             * subgroups : []
             * items : [{"itemId":"841","itemName":"Chachuka","itemGroupId":"2","itemPrice":"65","itemImage":"shakshuka.jpg"},{"itemId":"65","itemName":"Couple Morning","itemGroupId":"2","itemPrice":"116","itemImage":"couplebreakfast.jpg"},{"itemId":"66","itemName":"Couple Omlet","itemGroupId":"2","itemPrice":"124","itemImage":"couplebreakfast.jpg"},{"itemId":"85","itemName":"Italian Toast","itemGroupId":"2","itemPrice":"49","itemImage":"toast.jpg"}]
             */

            private String groupId;
            private boolean isSelect;
            private String groupName;
            private String groupParentId;
            private String groupImage;
            private List<?> subgroups;
            private List<ItemsBean> items;

            protected GalleryBean(Parcel in) {
                groupId = in.readString();
                isSelect = in.readByte() != 0;
                groupName = in.readString();
                groupParentId = in.readString();
                groupImage = in.readString();
            }

            public static final Creator<GalleryBean> CREATOR = new Creator<GalleryBean>() {
                @Override
                public GalleryBean createFromParcel(Parcel in) {
                    return new GalleryBean(in);
                }

                @Override
                public GalleryBean[] newArray(int size) {
                    return new GalleryBean[size];
                }
            };

            public String getGroupId() {
                return groupId;
            }

            public void setGroupId(String groupId) {
                this.groupId = groupId;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getGroupParentId() {
                return groupParentId;
            }

            public void setGroupParentId(String groupParentId) {
                this.groupParentId = groupParentId;
            }

            public String getGroupImage() {
                return groupImage;
            }

            public void setGroupImage(String groupImage) {
                this.groupImage = groupImage;
            }

            public List<?> getSubgroups() {
                return subgroups;
            }

            public void setSubgroups(List<?> subgroups) {
                this.subgroups = subgroups;
            }

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(groupId);
                dest.writeByte((byte) (isSelect ? 1 : 0));
                dest.writeString(groupName);
                dest.writeString(groupParentId);
                dest.writeString(groupImage);
            }

            public static class ItemsBean implements Parcelable{
                /**
                 * itemId : 841
                 * itemName : Chachuka
                 * itemGroupId : 2
                 * itemPrice : 65
                 * itemImage : shakshuka.jpg
                 */

                private String itemId;
                private String itemName;
                private String itemGroupId;
                private String itemPrice;
                private String itemImage;

                protected ItemsBean(Parcel in) {
                    itemId = in.readString();
                    itemName = in.readString();
                    itemGroupId = in.readString();
                    itemPrice = in.readString();
                    itemImage = in.readString();
                }

                public static final Creator<ItemsBean> CREATOR = new Creator<ItemsBean>() {
                    @Override
                    public ItemsBean createFromParcel(Parcel in) {
                        return new ItemsBean(in);
                    }

                    @Override
                    public ItemsBean[] newArray(int size) {
                        return new ItemsBean[size];
                    }
                };

                public String getItemId() {
                    return itemId;
                }

                public void setItemId(String itemId) {
                    this.itemId = itemId;
                }

                public String getItemName() {
                    return itemName;
                }

                public void setItemName(String itemName) {
                    this.itemName = itemName;
                }

                public String getItemGroupId() {
                    return itemGroupId;
                }

                public void setItemGroupId(String itemGroupId) {
                    this.itemGroupId = itemGroupId;
                }

                public String getItemPrice() {
                    return itemPrice;
                }

                public void setItemPrice(String itemPrice) {
                    this.itemPrice = itemPrice;
                }

                public String getItemImage() {
                    return itemImage;
                }

                public void setItemImage(String itemImage) {
                    this.itemImage = itemImage;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(itemId);
                    dest.writeString(itemName);
                    dest.writeString(itemGroupId);
                    dest.writeString(itemPrice);
                    dest.writeString(itemImage);
                }
            }
        }
    }
}
