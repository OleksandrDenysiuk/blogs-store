package com.portfolio.blogsstore.controller;


import com.portfolio.blogsstore.domain.Article;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    @GetMapping("article/{id}/image")
    public void renderImageRecipeFromDB(@PathVariable("id") Article article, HttpServletResponse response) throws IOException {

        renderImage(response, article.getImage());
    }


    @GetMapping("step/{id}/image")
    public void renderImageStepFromDB(@PathVariable("id") Article article, HttpServletResponse response) throws IOException {
        renderImage(response, article.getImage());
    }

    private void renderImage(HttpServletResponse response, Byte[] image) throws IOException {
        if (image != null) {
            byte[] byteArray = new byte[image.length];
            int i = 0;

            for (Byte wrappedByte : image){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }

}
