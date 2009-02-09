package com.chuidiang.ejemplos.hibernate.ejemplo1;

import java.util.Date;

public class Event {
        private Long id;
        private String title;
        private Date date;
        public Event() {
            // TODO Auto-generated constructor stub
        }
        public Long getId() {
            return id;
        }
        private void setId(Long id) {
            this.id = id;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public Date getDate() {
            return date;
        }
        public void setDate(Date date) {
            this.date = date;
        }
        public String toString() {
            return id + " - " + title + " - " + date;
        }
}
