package com.asgar.tanggapbencana.model;

import java.util.List;

/**
 * Created by irfanandarafifsatrio on 9/26/16.
 */

public class ResponseListDao {

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private String next_page_url;
    private String prev_page_url;
    private int from;
    private int to;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(String prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String nama_lokasi;
        private Object deskripsi_korban;
        private Object deskripsi_kebutuhan;
        private String keterangan;
        private String nama_relawan;
        private String created_at;

        public String getNama_lokasi() {
            return nama_lokasi;
        }

        public void setNama_lokasi(String nama_lokasi) {
            this.nama_lokasi = nama_lokasi;
        }

        public Object getDeskripsi_korban() {
            return deskripsi_korban;
        }

        public void setDeskripsi_korban(Object deskripsi_korban) {
            this.deskripsi_korban = deskripsi_korban;
        }

        public Object getDeskripsi_kebutuhan() {
            return deskripsi_kebutuhan;
        }

        public void setDeskripsi_kebutuhan(Object deskripsi_kebutuhan) {
            this.deskripsi_kebutuhan = deskripsi_kebutuhan;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }

        public String getNama_relawan() {
            return nama_relawan;
        }

        public void setNama_relawan(String nama_relawan) {
            this.nama_relawan = nama_relawan;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
