//package uk.ac.gcu.ssd.controllers;
//
//import java.io.IOException;
//import java.util.stream.Collectors;
//
////import com.sun.org.slf4j.internal.Logger;
////import com.sun.org.slf4j.internal.LoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
////import uk.ac.gcu.ssd.storage.StorageFileNotFoundException;
//import uk.ac.gcu.ssd.storage.StorageService;
//
//@RequestMapping("/upload")
//@Controller
//public class FileUploadController {
//  private final StorageService storageService;
//  private String UPLOAD_FOLDER;
//  private Logger logger = LoggerFactory.getLogger(FileUploadController.class);
//  @Lazy
//  @Autowired
//  public FileUploadController(StorageService storageService) {
//    this.storageService = storageService;
//  }
//
//  @GetMapping("/files")
//  public String listUploadedFiles(Model model) throws IOException {
//
//    model.addAttribute("files", storageService.loadAll().map(
//        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//            "serveFile", path.getFileName().toString()).build().toString())
//        .collect(Collectors.toList()));
//
//    return "uploadForm";
//  }
//
//  @GetMapping("/files/{filename:.+}")
//  @ResponseBody
//  public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//    Resource file = storageService.loadAsResource(filename);
//    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//        "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//  }
//
//  @PostMapping("/file")
//  public String handleFileUpload(@RequestParam("file") MultipartFile file,
//                                 RedirectAttributes redirectAttributes) {
////    logger.debug("传入的文件参数：{}", JSON.toJSONString(file, true));
//    storageService.store(file);
//    redirectAttributes.addFlashAttribute("message",
//        "You successfully uploaded " + file.getOriginalFilename() + "!");
//
//    return "redirect:/";
//  }
//
////  @ExceptionHandler(StorageFileNotFoundException.class)
////  public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
////    return ResponseEntity.notFound().build();
////  }
//
//}
