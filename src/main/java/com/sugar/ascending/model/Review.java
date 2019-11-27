package com.sugar.ascending.model;

import java.time.LocalDate;

public class Review {
    private int b_id;
    private int c_id;
    private int rate;
    private String content;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Review{" +
                "b_id=" + b_id +
                ", c_id=" + c_id +
                ", rate=" + rate +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
