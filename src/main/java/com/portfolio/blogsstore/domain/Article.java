package com.portfolio.blogsstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Article")
public class Article {

    @Id
    private String id;

    private String title;

    private String content;

    private Byte[] image;

    @DBRef
    private User author;

    private Date date;

    private Set<String> likedUsersId = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) &&
                Objects.equals(title, article.title) &&
                Objects.equals(content, article.content) &&
                Objects.equals(author, article.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, author);
    }

    public String getFormattedDate(){
        DateFormat df = new SimpleDateFormat("MMMM dd, yyyy", new Locale("en"));
        return df.format(getDate());
    }

    public boolean isLikedUser(User user){
        for(String likedUserId : likedUsersId){
            if(likedUserId.equals(user.getId())){
                return true;
            }
        }
        return false;
    }
}
