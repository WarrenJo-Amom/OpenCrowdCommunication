package in.amant.opencrowdcommunication.model;

import android.graphics.drawable.Drawable;

public class ListViewEventItem {

    private Drawable iconDrawable ;
    private String titleStr ;
    private String descStr ;
    private String descStr2 ;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setDesc2(String desc2) {
        descStr2 = desc2 ;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public String getDesc2() {
        return this.descStr2 ;
    }

}