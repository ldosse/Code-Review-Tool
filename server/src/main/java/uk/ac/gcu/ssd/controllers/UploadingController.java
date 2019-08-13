//package uk.ac.gcu.ssd.beer;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//
//@RestController
//public class UploadingController {
//  public static final String uploadingDir = System.getProperty("user.dir") + "/uploadingDir/";
//
//  @CrossOrigin(origins = "http://localhost:8081")
//  @GetMapping("/files")
////  @RequestMapping("/uploading/")
//  public String uploading(Model model) {
//    File file = new File(uploadingDir);
//    model.addAttribute("files", file.listFiles());
//    return "uploading";
//  }
//
//  //  @RequestMapping(value = "/uploading/", method = RequestMethod.POST)
//  @CrossOrigin(origins = "http://localhost:8081")
//  @PostMapping("/uploading")
//  public String uploadingPost(MultipartFile[] uploadingFiles) throws IOException {
//    for(MultipartFile uploadedFile : uploadingFiles) {
//      File file = new File(uploadingDir + uploadedFile.getOriginalFilename());
//      uploadedFile.transferTo(file);
//    }
//    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//    return "redirect:/";
//  }
//}
