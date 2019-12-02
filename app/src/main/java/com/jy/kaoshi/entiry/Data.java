package com.jy.kaoshi.entiry;

import com.jy.utils.Logger;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/*
 * created by Cherry on 2019-12-02
 **/
@Entity
public class Data {

    @Id
    private Long id;

    @Property(nameInDb = "dataId")
    private String _id;
    private String desc;
    private String url;
    private boolean isSelected; // true 表示选择，false 表示未选中
    @Generated(hash = 895709325)
    public Data(Long id, String _id, String desc, String url, boolean isSelected) {
        this.id = id;
        this._id = _id;
        this.desc = desc;
        this.url = url;
        this.isSelected = isSelected;
    }
    @Generated(hash = 2135787902)
    public Data() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String get_id() {
        return this._id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public boolean getIsSelected() {
        return this.isSelected;
    }
    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }



}
