package com.example.listam.controller;

import com.example.listam.entity.CategoryEntity;
import com.example.listam.entity.CommentEntity;
import com.example.listam.entity.ItemEntity;
import com.example.listam.repository.CategoryRepository;
import com.example.listam.repository.CommentRepository;
import com.example.listam.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/items")
    public String itemsPage(ModelMap modelMap) {
        List<ItemEntity> all = itemRepository.findAll();
        modelMap.addAttribute("items", all);
        return "items";
    }

    @GetMapping("/items/{id}")
    public String singleItemPage(@PathVariable("id") int id,
                                 ModelMap modelMap) {
        Optional<ItemEntity> byId = itemRepository.findById(id);
        if (byId.isPresent()) {
            ItemEntity item = byId.get();

            List<CommentEntity> all = commentRepository.findAll();
            modelMap.addAttribute("comments", all);

            modelMap.addAttribute("item", item);
            return "singleItem";
        } else {
            return "redirect:/items";
        }
    }

    @GetMapping("/items/add")
    public String itemsAddPage(ModelMap modelMap) {
        List<CategoryEntity> all = categoryRepository.findAll();
        modelMap.addAttribute("categories", all);
        return "addItem";
    }

    @PostMapping("/items/add")
    public String itemsAdd(@ModelAttribute ItemEntity item) {
        itemRepository.save(item);
        return "redirect:/items";
    }

    @GetMapping("/items/remove")
    public String removeCategory(@RequestParam("id") int id) {
        itemRepository.deleteById(id);
        return "redirect:/items";
    }
}