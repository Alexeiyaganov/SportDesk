package com.example.SportDesk.domain;


import com.example.SportDesk.domain.util.MessageHelper;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.geo.Point;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity // This tells Hibernate to make a table out of this class
//@Table(name="message")
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message="Введите название мероприятии")
    @Length(max=500, message="Информация слишком большая (больше 2kB)")
    private String name;

    @NotBlank(message="Введите информацию о мероприятии")
    @Length(max=2048, message="Информация слишком большая (больше 2kB)")
    private String text;

    private Timestamp date;

    private Timestamp lastupdate;

    @Length(max=200, message="Информация слишком большая (больше 2kB)")
    private String latitude;

    @Length(max=200, message="Информация слишком большая (больше 2kB)")
    private String longitude;

    @Length(max=250, message="Информация слишком большая (больше 2kB)")
    private String location;


    @Length(max=255, message="Тэг слишком длинный")
    private  String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id")
    private User author;


    @ManyToMany
    @JoinTable(
            name = "message_likes",
            joinColumns={@JoinColumn(name="message_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> likes = new HashSet<>();

    private String filename;


    public Message() {
    }

    public Message(String name, String text, String tag, Timestamp date, User user, String latitude, String longitude, String location) {
        this.name=name;
        this.author=user;
        this.text = text;
        this.tag = tag;
        this.date=date;
        this.latitude=latitude;
        this.longitude=longitude;
        this.location=location;
    }

    public String getAuthorName(){
        return MessageHelper.getAuthorName(author);
    }
    public Long getAuthorId(){
        return author!=null ? author.getId() : -1;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


    public void setText(String text){
        this.text=text;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getDate() {
        return date;
    }
    public LocalDateTime setLocalDateTime(){
        return date.toLocalDateTime();
    }

    public Timestamp getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Timestamp lastupdate) {
        this.lastupdate = lastupdate;
    }



    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(Point location) {
        this.latitude = String.valueOf(location.getX());
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(Point location) {
        this.longitude = String.valueOf(location.getY());
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }
}
