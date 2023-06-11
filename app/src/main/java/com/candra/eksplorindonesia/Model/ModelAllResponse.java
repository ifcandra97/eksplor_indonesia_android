package com.candra.eksplorindonesia.Model;

import java.util.List;

public class ModelAllResponse
{
    private String kode, pesan;
    private List<ModelKuliner> dataKuliner;
    private List<ModelUser> dataUser;
    private List<ModelWisata> dataWisata;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelKuliner> getDataKuliner() {
        return dataKuliner;
    }

    public List<ModelUser> getDataUser() {
        return dataUser;
    }

    public List<ModelWisata> getDataWisata() {
        return dataWisata;
    }
}
