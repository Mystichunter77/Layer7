package com.mystic.layer7;

import com.mystic.layer7.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//mvc format
@Controller
public class HomeController
{

    @Autowired
    private DatabaseService dbService;

    @GetMapping("/login")
    public String loginPage()
    {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage()
    {
        return "signup";
    }

    @GetMapping("/forums")
    public String forumsPage(Model model)
    {
        List<Post> posts = dbService.getPostRepository().findAll();
        System.out.println(posts);
        model.addAttribute("posts",posts);
        return "forums";
    }

    @GetMapping("/links")
    public String linksPage(Model model)
    {
        List<Link> links = dbService.getLinkRepository().findAll();
        model.addAttribute("links", links);
        return "links";
    }

    @GetMapping("/playlists")
    public String playlistsPage(Model model)
    {
        List<Playlist> playlists = dbService.getPlaylistRepository().findAll();
        model.addAttribute("playlists",playlists);
        return "playlists";
    }

    @GetMapping("/users")
    public String usersPage(Model model)
    {
        List<User> users = dbService.getUserRepository().findAll();
        model.addAttribute("users",users);
        return "users";
    }

    @GetMapping("/profile")
    public String showProfile(@CookieValue(value = "userid") String userId,
                              Model model)
    {
        User user = dbService.getUserRepository().getUserById(userId);
        model.addAttribute(user);
        return "profile";
    }
    @PostMapping("/createAccount")
    public String newAccount(@ModelAttribute("user") User user)
    {
        if(user == null)
        {
            return "error";
        }
        System.out.println(user);
        String password = user.getPassword();
        System.out.println(password);
        user.setPassword(new PasswordEncryptor().encode(user.getPassword()));
        user.setRole("USER");
        user.setPosts(new ArrayList<String>());
        System.out.println(user);
        dbService.getUserRepository().save(user);
        return "/index";
    }


    @PostMapping("/auth")
    public String profilePage(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpServletResponse response,
                              Model model)
    {
        try
        {
            PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
            User user = dbService.getUserRepository().getUserByUsername(username);
            String hashedPassword = passwordEncryptor.encode(password);
            System.out.println(hashedPassword);
            if (passwordEncryptor.comparePasswords(hashedPassword, user.getPassword()))
            {
                System.out.println("condition is evaluated to be true");
                System.out.println("retrieving posts");
                model.addAttribute(user);
                Cookie cookie = new Cookie("userid", user.getId());
                response.addCookie(cookie);
                return "profile";
            }
            else
            {
                System.out.println("evaluated as wrong");
                return "login";
            }
        }
        catch (Exception e)
        {
            return "error";
        }
    }

    @GetMapping("/addPost")
    public String addPost(@CookieValue(value = "userid") String userid,
                          Model model)
    {
        return "new-post-form";
    }

    @GetMapping("/addLink")
    public String addLink(@CookieValue(value = "userid") String userid,
                          Model model)
    {
        return "new-link-form";
    }

    @GetMapping("/addPlaylist")
    public String addPlaylist(@CookieValue(value = "userid") String userid,
                              Model model)
    {
        return "new-playlist-form";
    }

    @PostMapping("/addPost")
    public String addNewPost(@ModelAttribute("post") Post post,
                             @CookieValue(value = "userid") String userid,
                             Model model)
    {
        String s = post.getTags().get(0);
        post.setTags(Arrays.asList(s.split(" ")));
        Vote vote = new Vote();
        dbService.getVoteRepository().save(vote);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        post.setTimestamp(timestamp.toString());

        List<String> arr = new ArrayList<>();
        post.setComments(arr);
        post.setVote(vote.getId());
        post.setUser(userid);
        dbService.getPostRepository().save(post);
        System.out.println(post);
        return "forums";
    }

    @PostMapping("/addLink")
    public String addNewLink(@ModelAttribute("link") Link link,
                             Model model)
    {
        dbService.getLinkRepository().save(link);
        return "links";
    }

    @PostMapping("/addPlaylist")
    public String addNewPlaylist(@ModelAttribute("playlist") Playlist playlist,
                                 Model model)
    {
        dbService.getPlaylistRepository().save(playlist);
        return "playlists";
    }
}
