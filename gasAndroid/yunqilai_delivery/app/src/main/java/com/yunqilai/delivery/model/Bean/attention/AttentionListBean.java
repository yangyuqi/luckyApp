package com.yunqilai.delivery.model.Bean.attention;

import java.util.List;

/**
 * Created by yangyuqi on 17-8-2.
 */

public class AttentionListBean {
    private int count ;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ParseAttestation> getAttestationList() {
        return attestationList;
    }

    public void setAttestationList(List<ParseAttestation> attestationList) {
        this.attestationList = attestationList;
    }

    public AttentionListBean(int count, List<ParseAttestation> attestationList) {

        this.count = count;
        this.attestationList = attestationList;
    }

    private List<ParseAttestation> attestationList;

}
