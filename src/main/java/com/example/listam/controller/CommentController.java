package com.example.listam.controller;

import com.example.listam.entity.CommentEntity;
import com.example.listam.entity.ItemEntity;
import com.example.listam.repository.CommentRepository;
import com.example.listam.repository.ItemRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comments/remove")
    public String removeComment(@RequestParam("id") int id) {
        Optional<CommentEntity> byId = commentRepository.findById(id);
        int itemId = byId.get().getItem().getId();
        commentRepository.deleteById(id);

        return "redirect:/items/" + itemId;
    }

    @PostMapping("/comment/add")
    public String commentAdd(@RequestParam("item_id") int itemId, @ModelAttribute CommentEntity comment) {
        Optional<ItemEntity> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            ItemEntity item = optionalItem.get();
            comment.setItem(item);
            commentRepository.save(comment);
            return "redirect:/items/" + itemId;
        } else {
            return "redirect:/items/";
        }
    }
}


