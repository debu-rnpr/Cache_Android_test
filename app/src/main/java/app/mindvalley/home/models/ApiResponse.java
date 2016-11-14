package app.mindvalley.home.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by debu on 11/11/16.
 */
public class ApiResponse implements Serializable
{
    private String id;

    private String height;

    private ArrayList<String> current_user_collections;

    private String color;

    private Urls urls;

    private String likes;

    private String width;

    private String created_at;

    private Links links;

    private ArrayList<Categories> categories;

    private User user;

    private String liked_by_user;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    public ArrayList<String> getCurrent_user_collections ()
    {
        return current_user_collections;
    }

    public void setCurrent_user_collections (ArrayList<String> current_user_collections)
    {
        this.current_user_collections = current_user_collections;
    }

    public String getColor ()
    {
        return color;
    }

    public void setColor (String color)
    {
        this.color = color;
    }

    public Urls getUrls ()
    {
        return urls;
    }

    public void setUrls (Urls urls)
    {
        this.urls = urls;
    }

    public String getLikes ()
    {
        return likes;
    }

    public void setLikes (String likes)
    {
        this.likes = likes;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public Links getLinks ()
    {
        return links;
    }

    public void setLinks (Links links)
    {
        this.links = links;
    }

    public ArrayList<Categories> getCategories ()
    {
        return categories;
    }

    public void setCategories (ArrayList<Categories> categories)
    {
        this.categories = categories;
    }

    public User getUser ()
    {
        return user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    public String getLiked_by_user ()
    {
        return liked_by_user;
    }

    public void setLiked_by_user (String liked_by_user)
    {
        this.liked_by_user = liked_by_user;
    }

    public class Links implements Serializable
    {
        private String photos;

        private String likes;

        private String html;

        private String self;

        public String getPhotos ()
        {
            return photos;
        }

        public void setPhotos (String photos)
        {
            this.photos = photos;
        }

        public String getLikes ()
        {
            return likes;
        }

        public void setLikes (String likes)
        {
            this.likes = likes;
        }

        public String getHtml ()
        {
            return html;
        }

        public void setHtml (String html)
        {
            this.html = html;
        }

        public String getSelf ()
        {
            return self;
        }

        public void setSelf (String self)
        {
            this.self = self;
        }
    }

    public class Categories implements Serializable
    {
        private String id;

        private String title;

        private Links links;

        private String photo_count;

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getTitle ()
        {
            return title;
        }

        public void setTitle (String title)
        {
            this.title = title;
        }

        public Links getLinks ()
        {
            return links;
        }

        public void setLinks (Links links)
        {
            this.links = links;
        }

        public String getPhoto_count ()
        {
            return photo_count;
        }

        public void setPhoto_count (String photo_count)
        {
            this.photo_count = photo_count;
        }
    }

    public class User implements Serializable
    {
        private String id;

        private String username;

        private Profile_image profile_image;

        private String name;

        private Links links;

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getUsername ()
        {
            return username;
        }

        public void setUsername (String username)
        {
            this.username = username;
        }

        public Profile_image getProfile_image ()
        {
            return profile_image;
        }

        public void setProfile_image (Profile_image profile_image)
        {
            this.profile_image = profile_image;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public Links getLinks ()
        {
            return links;
        }

        public void setLinks (Links links)
        {
            this.links = links;
        }

        public class Profile_image implements Serializable
        {
            private String small;

            private String large;

            private String medium;

            public String getSmall ()
            {
                return small;
            }

            public void setSmall (String small)
            {
                this.small = small;
            }

            public String getLarge ()
            {
                return large;
            }

            public void setLarge (String large)
            {
                this.large = large;
            }

            public String getMedium ()
            {
                return medium;
            }

            public void setMedium (String medium)
            {
                this.medium = medium;
            }
        }
    }

    public class Urls implements Serializable
    {
        private String raw;

        private String regular;

        private String full;

        private String thumb;

        private String small;

        public String getRaw ()
        {
            return raw;
        }

        public void setRaw (String raw)
        {
            this.raw = raw;
        }

        public String getRegular ()
        {
            return regular;
        }

        public void setRegular (String regular)
        {
            this.regular = regular;
        }

        public String getFull ()
        {
            return full;
        }

        public void setFull (String full)
        {
            this.full = full;
        }

        public String getThumb ()
        {
            return thumb;
        }

        public void setThumb (String thumb)
        {
            this.thumb = thumb;
        }

        public String getSmall ()
        {
            return small;
        }

        public void setSmall (String small)
        {
            this.small = small;
        }
    }

}
