package com.g1.curso.controllers;

import com.g1.curso.dtos.PostDTO;
import com.g1.curso.models.PostModel;
import com.g1.curso.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@CrossOrigin("${spring.url}")
public class PostController {
    @Value("${spring.urlPort}")
    private String url;
    @Autowired
    PostRepository postRepository;

    @PostMapping("/post/create")
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO) {
        PostModel post = new PostModel();
        post.setTitle(postDTO.title());
        post.setDescription(postDTO.description());
        UUID uuid = UUID.randomUUID();
        post.setUuid(uuid);

        String directoryPath = "./src/main/resources/static/posts/" + uuid;

        File dir = new File(directoryPath);
        File createImgDir = new File(directoryPath + "/img");
        File createVideoDir = new File(directoryPath + "/video");

        if (!dir.exists()) {
            dir.mkdirs();
            createImgDir.mkdir();
            createVideoDir.mkdir();

            System.out.println(postDTO.videos().size());

            for(int i =0;i < postDTO.videos().size();i++ ){
                String mediaBase64 = postDTO.videos().get(i);
                System.out.println("Valor Precisa Ser 0");
                System.out.println(i);

                if (mediaBase64.startsWith("data:video/mp4;base64")){
                    System.out.println("Video Aqui");
                    String videoBase64 = mediaBase64.replace("data:video/mp4;base64,", "");
                    byte[] videoBytes = Base64.getDecoder().decode(videoBase64);
                    String videoPath = directoryPath + "/video/video_" + i + ".mp4";

                    try {
                        FileOutputStream fos = new FileOutputStream(videoPath);
                        fos.write(videoBytes);
                        fos.close();
                        System.out.println("Vídeo salvo com sucesso em: " + videoPath);
                        post.setImgs(directoryPath + "/video");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            for (int i = 0; i < postDTO.imgs().size(); i++) {
                String mediaBase64 = postDTO.imgs().get(i);

                if (mediaBase64.startsWith("data:image/jpeg;base64")) {
                    System.out.println("Imagem Aqui");
                    String imgBase64 = mediaBase64.replace("data:image/jpeg;base64,", "");
                    byte[] imageBytes = Base64.getDecoder().decode(imgBase64);
                    String imgPath = directoryPath + "/img/img_" + i + ".jpeg";

                    try {
                        FileOutputStream fos = new FileOutputStream(imgPath);
                        fos.write(imageBytes);
                        fos.close();
                        System.out.println("Imagem salva com sucesso em: " + imgPath);
                        post.setImgs(directoryPath + "/img");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(mediaBase64.startsWith("data:image/png;base64")){
                    System.out.println("Imagem Aqui");
                    String imgBase64 = mediaBase64.replace("data:image/png;base64,", "");
                    byte[] imageBytes = Base64.getDecoder().decode(imgBase64);
                    String imgPath = directoryPath + "/img/img_" + i + ".jpeg";

                    try {
                        FileOutputStream fos = new FileOutputStream(imgPath);
                        fos.write(imageBytes);
                        fos.close();
                        System.out.println("Imagem salva com sucesso em: " + imgPath);
                        post.setImgs(directoryPath + "/img");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
            LocalDateTime currentDateTime = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);

        post.setCreatedat(formattedDateTime);
        postRepository.save(post);
        return ResponseEntity.ok("Post Created");
    }
    @GetMapping("/post/list")
    public ResponseEntity<List<PostModel>> listPost(){

        List<PostModel> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdat"));

        return ResponseEntity.ok(posts);
    }
    @GetMapping("/post/get/{uuid}")
    public ResponseEntity<PostModel> getPost(@PathVariable UUID uuid){
        Optional<PostModel> post = postRepository.findById(uuid);

        return ResponseEntity.ok(post.get());
    }
    @GetMapping("/post/midia/{uuid}")
    public ResponseEntity<Map<String, List<String>>> getMidia(@PathVariable UUID uuid) {
        String basePath = "./src/main/resources/static/posts/" + uuid;
        Map<String, List<String>> mediaUrls = new HashMap<>();
        mediaUrls.put("imgs", new ArrayList<>());
        mediaUrls.put("videos", new ArrayList<>());

        File imgDir = new File(basePath + "/img");
        File[] imgFiles = imgDir.listFiles();
        if (imgFiles != null) {
            for (File imgFile : imgFiles) {
                String imgUrl = url + "/posts/" + uuid + "/img/" + imgFile.getName();
                mediaUrls.get("imgs").add(imgUrl);
            }
        }

        File videoDir = new File(basePath + "/video");
        File[] videoFiles = videoDir.listFiles();
        if (videoFiles != null) {
            for (File videoFile : videoFiles) {
                String videoUrl = url + "/posts/" + uuid + "/video/" + videoFile.getName();
                mediaUrls.get("videos").add(videoUrl);
            }
        }

        return ResponseEntity.ok(mediaUrls);
    }

}
