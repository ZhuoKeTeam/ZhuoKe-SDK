package team.zhuoke.sdk.notification;


import java.io.Serializable;

import androidx.annotation.DrawableRes;

/**
 * @author Yunr
 * @date 2018/03/02 15:47
 */
public class ItemDataBean implements Serializable {

    private String text;

    private @DrawableRes
    int imgId;

    private String itemId;

    public ItemDataBean(String text, int imgId, String itemId) {
        this.text = text;
        this.imgId = imgId;
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    @Override
    public String toString() {
        return "ItemDataBean{" +
                "text='" + text + '\'' +
                ", imgId=" + imgId +
                ", itemId='" + itemId + '\'' +
                '}';
    }
}
