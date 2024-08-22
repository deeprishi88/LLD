import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Player> playerList = new ArrayList<>();
        EventController eventController = new EventController();

        Scanner sc = new Scanner(System.in);
        for(int i=0;i<=5;i++){
            String str = sc.nextLine();
            String[] input = str.split(" ");

            if(input[0].equals("ADD_MEMBER")){
                Player player = new Player(Integer.parseInt(input[1]), input[2], Integer.parseInt(input[3]));
                playerList.add(player);
                System.out.println(player.getName() + " added successfully");
            }
            if(input[0].equals("ADD_EVENT")){
                eventController.addEvent(Integer.parseInt(input[1]), input[2], input[3], input[4]);
            }
            if(input[0].equals("REGISTER_MEMBER")){
                int playerId = Integer.parseInt(input[1]);
                int eventId = Integer.parseInt(input[2]);

                for(Player player : playerList){
                    if(player.getMember_id() == playerId){
                        eventController.addPlayerToEvent(eventId, player);
                        break;
                    }
                }
            }
            if(input[0].equals("SUBMIT_BID")){

                int playerId = Integer.parseInt(input[1]);
                int eventId = Integer.parseInt(input[2]);

                List<Integer> bids = new ArrayList<>();
                for(int b=3;b<input.length;b++){
                    bids.add(Integer.parseInt(input[b]));
                }

                Player curPlayer = null;
                for(Player player : playerList){
                    if(player.getMember_id() == playerId){
                        curPlayer = player;
                        eventController.addBidsForEvent(eventId, player, bids);
                        break;
                    }
                }

                if(curPlayer == null){
                    System.out.println("Member not added");
                }
            }
            if(input[0].equals("DECLARE_WINNER")){
                int eventId = Integer.parseInt(input[1]);
                Winner winner = eventController.findWinner(eventId);
                if(winner == null){
                    continue;
                }
                for(Player player : playerList){
                    if(Objects.equals(player.getName(), winner.getWinner_name())){
                        player.setSuperCoins(player.getSuperCoins() - winner.getLowest_bid());
                    }
                }
            }
            if(input[0].equals("LIST_WINNERS")){
                String order = input[1];
                eventController.printWinnerList(order);
            }
        }

    }

}

class EventController {
    List<Event> eventList;
    List<Winner> winnerList;

    public EventController(){
        this.eventList = new ArrayList<>();
        this.winnerList = new ArrayList<>();
    }

    public void addEvent(int id, String name, String price, String date){
        boolean ifDateDone = false;

        for(Event event : eventList){
            if(Objects.equals(event.getDate(), date)){
                ifDateDone = true;
                break;
            }
        }

        if(ifDateDone){
            System.out.println("Event already created for this date");
        }

        Event event = new Event(id, name, price, date);
        eventList.add(event);
        System.out.println(event.getEventName() + " with prize " + event.getEventPrice() + " added successfully");
    }

    public void addPlayerToEvent(int eventId, Player player){
        for(Event event : eventList){
            if(event.getEvent_id() == eventId){
                event.registerPlayer(player);
                break;
            }
        }
    }

    public void addBidsForEvent(int eventId, Player player, List<Integer> bids){
        for(Event event : eventList){
            if(event.getEvent_id() == eventId){
                event.addBids(player, bids);
                break;
            }
        }
    }

    public Winner findWinner(int eventId){
        Winner winner = null;
        Event curEvent = null;
        for(Event event : eventList){
            if(event.getEvent_id() == eventId){
                curEvent = event;
                winner = event.eventWinner();
                break;
            }
        }
        winnerList.add(winner);
        if(curEvent != null) {
            System.out.println(winner.getWinner_name() + " wins the " + curEvent.getEventPrice() + " with lowest bid " + winner.getLowest_bid());
            return winner;
        }
        System.out.println("No event found");
        return null;
    }

    public void printWinnerList(String order){
        if(order.equals("asc")){
            winnerList.sort(Comparator.comparing(Winner::getDate));
        }
        else{
            winnerList.sort(Comparator.comparing(Winner::getDate).reversed());
        }

        for(Winner winner : winnerList){
            System.out.println("{ " + winner.getEvent_id() +", " + winner.getWinner_name() + ", " +
                    winner.getLowest_bid() + ", " + winner.getDate() + " }");
        }
    }

}

class Event {
    int event_id;
    String eventName;
    String eventPrice;
    String date;
    List<Player> registeredPlayers;
    HashMap<Player, List<Pair>> playerVsBids;

    public Event(int event_id, String eventName, String eventPrice, String date) {
        this.event_id = event_id;
        this.eventName = eventName;
        this.eventPrice = eventPrice;
        this.date = date;
        this.registeredPlayers = new ArrayList<>();
        this.playerVsBids = new HashMap<>();
    }

    public int getEvent_id() {
        return event_id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventPrice() {
        return eventPrice;
    }

    public String getDate() {
        return date;
    }

    public List<Player> getRegisteredPlayers() {
        return registeredPlayers;
    }

    public HashMap<Player, List<Pair>> getPlayerVsBids() {
        return playerVsBids;
    }

    public void registerPlayer(Player player){
        registeredPlayers.add(player);
        System.out.println(player.getName() + " registered to the " + eventName + " successfully");
    }

    public void addBids(Player player, List<Integer> bids){
        boolean ifPresent = false;
        for(Player player1 : registeredPlayers){
            if(Objects.equals(player1.getName(), player.getName()) && player1.getMember_id() == player.getMember_id()){
                ifPresent = true;
                break;
            }
        }

        if(!ifPresent){
            System.out.println("Member did not registered for this event");
            return;
        }

        for(int bid = 0;bid<bids.size(); bid++){
            if(bids.get(bid)<0 || bids.get(bid)>player.getSuperCoins()){
                System.out.println("Invalid Bid");
                return;
            }
        }

        int curTime = 0;
        int cnt=0;
        for(Map.Entry<Player, List<Pair>> entry : playerVsBids.entrySet()){
            if(Objects.equals(entry.getKey().getName(), player.getName()) &&
                    entry.getKey().getMember_id() == player.getMember_id()){
                cnt++;
            }
            List<Pair> values = entry.getValue();
            for(Pair p : values){
                int time = p.getTime();
                if(time>curTime){
                    curTime = time;
                }
            }
        }
        curTime+=1;

        int bidrem = 5 - cnt;

        for(int bid=0;bid<Math.min(bids.size(),bidrem);bid++){
            if(!playerVsBids.containsKey(player)){
                List<Pair> bd = new ArrayList<>();
                bd.add(new Pair(bids.get(bid), curTime));
                playerVsBids.put(player, bd);
            }
            playerVsBids.get(player).add(new Pair(bids.get(bid), curTime));
        }

        System.out.println("BIDS submitted successfully");
    }

    public Winner eventWinner(){
        String winnerName = "";
        String winnerDate = date;
        int winnerEventId = event_id;
        int lowest_bid = 100000000;
        int time = 1000000;

        for(Map.Entry<Player, List<Pair>> entry : playerVsBids.entrySet()){
            List<Pair> bidList = entry.getValue();
            for(Pair pair : bidList){
                if(pair.getBidAmount() < lowest_bid){
                    winnerName = entry.getKey().getName();
                    lowest_bid = pair.getBidAmount();
                    time = pair.getTime();
                }
                else if(pair.getBidAmount() == lowest_bid){
                    if(pair.getTime() < time) {
                        winnerName = entry.getKey().getName();
                        lowest_bid = pair.getBidAmount();
                        time = pair.getTime();
                    }
                }
            }
        }

        return new Winner(winnerEventId, winnerName, lowest_bid, winnerDate);
    }

}

class Player {
    int member_id;
    String name;
    int superCoins;

    public Player(int id, String name, int coins){
        this.member_id = id;
        this.name = name;
        this.superCoins = coins;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSuperCoins() {
        return superCoins;
    }

    public void setSuperCoins(int superCoins) {
        this.superCoins = superCoins;
    }
}

class Pair{
    int bidAmount;
    int time;
     public Pair(int bidAmount, int time){
         this.bidAmount = bidAmount;
         this.time = time;
     }

    public int getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(int bidAmount) {
        this.bidAmount = bidAmount;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

class Winner {
    int event_id;
    String winner_name;
    int lowest_bid;
    String date;

    public Winner(int event_id, String winner_name, int lowest_bid, String date) {
        this.event_id = event_id;
        this.winner_name = winner_name;
        this.lowest_bid = lowest_bid;
        this.date = date;
    }

    public int getEvent_id() {
        return event_id;
    }

    public String getWinner_name() {
        return winner_name;
    }

    public int getLowest_bid() {
        return lowest_bid;
    }

    public String getDate() {
        return date;
    }
}