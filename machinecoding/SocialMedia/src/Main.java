import java.time.Instant;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Facebook facebook = new Facebook();

        facebook.createUser(1);
        facebook.createUser(2);
        facebook.createUser(3);
        facebook.createUser(4);
        facebook.createUser(5);
        facebook.createUser(6);
        facebook.createUser(7);
        facebook.createUser(8);
        facebook.createUser(9);
        facebook.createUser(10);
        facebook.createUser(11);
        facebook.createUser(12);
        facebook.createUser(13);

        facebook.follow(1, 2);
        facebook.follow(1, 3);
        facebook.follow(1, 4);
        facebook.follow(1, 5);
        facebook.follow(1, 6);
        facebook.follow(1, 7);
        facebook.follow(1, 8);
        facebook.follow(1, 9);
        facebook.follow(1, 10);
        facebook.follow(1, 11);
        facebook.follow(1, 12);
        facebook.follow(1, 13);

        facebook.createPost(1, 1000);
        facebook.createPost(2, 1002);
        facebook.createPost(3, 1003);
        facebook.createPost(4, 1004);
        facebook.createPost(5, 1005);
        facebook.createPost(6, 1006);
        facebook.createPost(7, 1007);
        facebook.createPost(8, 1008);
        facebook.createPost(9, 1009);
        facebook.createPost(10, 1010);
        facebook.createPost(11, 1011);
        facebook.createPost(12, 1012);
        facebook.createPost(13, 1013);

        facebook.getNewsFeed(1);
        facebook.unfollow(1, 13);
        facebook.getNewsFeed(1);
        facebook.deletePost(12, 1012);
        facebook.getNewsFeed(1);
        facebook.getUserTimelinePaginated(1, 2);
        facebook.getUserTimelinePaginated(1, 5);
    }
}

class Facebook {
    List<User> userList;
    long time;

    public Facebook() {
        this.userList = new ArrayList<>();
        this.time = 0L;
    }

    public void createUser(int userId){
        User user = new User(userId);
        userList.add(user);
    }

    public void removeUser(int userId){
        for(User user : userList){
            if(user.getUserId() == userId){
                userList.remove(user);
            }
        }

        for(User user : userList){
            user.unfollow(userId);
        }
    }

    public void createPost(int userId, int postId){
        this.time = Instant.now().toEpochMilli();
        Post post = new Post(postId, time);

        for(User user : userList){
            if(user.getUserId() == userId){
                user.createPost(post);
            }
        }
    }

    public void deletePost(int userId, int postId){
        for(User user : userList){
            if(user.getUserId() == userId){
                user.deletePost(postId);
            }
        }
    }

    public void follow(int followerId, int followeeId){
        for(User user : userList){
            if(user.getUserId() == followerId){
                user.follow(followeeId);
            }
        }
    }

    public void unfollow(int followerId, int followeeId){
        for(User user : userList){
            if(user.getUserId() == followerId){
                user.unfollow(followeeId);
            }
        }
    }

    public void getNewsFeed(int userId){
        for(User user : userList){
            if(user.getUserId() == userId){
                user.getMyFeed();
            }
        }
    }

    public void getUserTimelinePaginated(int userId, int pageNumber){
        List<User> usersFollowed = new ArrayList<>();
        for(User user : userList){
            if(user.getUserId() == userId){
                for(int i=0;i<user.getFollowed().size();i++){
                    int followedUserId = user.getFollowed().get(i);
                    for(User user1 : userList){
                        if(user1.getUserId() == followedUserId){
                            usersFollowed.add(user1);
                        }
                    }
                }
            }
        }

        for(User user : userList){
            if(user.getUserId() == userId){
                user.getMyTimeline(usersFollowed, pageNumber);
            }
        }

    }

}

class User {
    int userId;
    List<Post> userPosts;
    List<Integer> followed;

    public User(int userId) {
        this.userId = userId;
        this.userPosts = new ArrayList<>();
        this.followed = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Post> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(List<Post> userPosts) {
        this.userPosts = userPosts;
    }

    public List<Integer> getFollowed() {
        return followed;
    }

    public void setFollowed(List<Integer> followed) {
        this.followed = followed;
    }

    public void createPost(Post post){
        userPosts.add(post);
        System.out.println(userId + " created a post " + post.getPostId() + " at " + post.getTime());
    }

    public void deletePost(int postId){
        for(Post post1 : userPosts){
            if(post1.getPostId() == postId){
                userPosts.remove(post1);
                System.out.println(userId + " deleted a post " + postId + " created at " + post1.getTime());
                return;
            }
        }
        System.out.println("No posts found");
    }

    public void follow(int followedUserId){
        followed.add(followedUserId);
        System.out.println(userId + " followed " + followedUserId);
    }

    public void unfollow(int unfollowUserId){
        for(int i=0;i<followed.size();i++){
            if(followed.get(i) == unfollowUserId){
                followed.remove(followed.get(i));
                System.out.println(userId + " unfollowed " + unfollowUserId);
                return;
            }
        }
    }

    public void getMyFeed(){
        List<Pair> myPosts = new ArrayList<>();

        for(Post post : userPosts){
            myPosts.add(new Pair(post.getTime(), post));
        }

        myPosts.sort(Comparator.comparing(Pair::getTime).reversed());

        System.out.println(userId + " Posts are : ");
        for(Pair pair : myPosts){
            System.out.println(pair.getTime() + " -> " + pair.getPost().getPostId());
        }
    }

    public void getMyTimeline(List<User> followedUsers, int pageNum) {
        List<Pair> myTimeline = new ArrayList<>();
        HashMap<Integer, List<Pair>> pageVsPosts = new HashMap<>();

        for(Post post : userPosts){
            myTimeline.add(new Pair(post.getTime(), post));
        }

        for(User user : followedUsers){
            List<Post> followedPost = user.getUserPosts();
            for(Post post : followedPost){
                myTimeline.add(new Pair(post.getTime(), post));
            }
        }

        myTimeline.sort(Comparator.comparing(Pair::getTime)
                .reversed());

        int page = 0;
        int cnt=0;
        for(Pair pair : myTimeline){
            if(cnt%10 == 0) {
                ++page;
            }
            cnt++;
            if(pageVsPosts.containsKey(page)){
                pageVsPosts.get(page).add(new Pair(pair.getTime(), pair.getPost()));
            }
            else{
                pageVsPosts.put(page, new ArrayList<>());
                pageVsPosts.get(page).add(new Pair(pair.getTime(), pair.getPost()));
            }
        }

//        for(Map.Entry<Integer, List<Pair>> entry : pageVsPosts.entrySet()){
//            System.out.println(entry.getKey() + "st page : ");
//            List<Pair> l = entry.getValue();
//            for(Pair p : l){
//                System.out.println(p.getPost().getPostId() + " " + p.getPost().getTime());
//            }
//        }

        if(!pageVsPosts.containsKey(pageNum)){
            System.out.println("This page number doesn't exist");
            return;
        }

        System.out.println(userId + "'s timeline at page number = " + pageNum + " is : ");

        List<Pair> postAtPage = pageVsPosts.get(pageNum);

        for(Pair pair : postAtPage){
            System.out.println(pair.getTime() + " -> " + pair.getPost().getPostId());
        }
    }
}

class Post {
    int postId;
    long time;

    public Post(int postId, long time) {
        this.time = time;
        this.postId = postId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

class Pair {
    long time;
    Post post;

    public Pair(long time, Post post) {
        this.time = time;
        this.post = post;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}