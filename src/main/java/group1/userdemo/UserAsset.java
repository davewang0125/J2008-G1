package group1.userdemo;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "userAsset")
@EntityListeners(AuditingEntityListener.class)
public class UserAsset {
    @Id
    @Column(name = "asset_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long assetId;//Each userAsset will be given an auto-generated unique identifier when stored

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "asset_type", nullable = false)
    private String assetType;

    @Column(name = "content", nullable = false)
    private String content;



    //getter && setter
    public long getUserId() {
        return userId;
    }

    public void setUserId(long value) {
        this.userId = value;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String value) {
        this.content = value;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

}

