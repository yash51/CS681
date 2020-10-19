package edu.umb.cs.cs681.hw04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//ordering players/teams based on some stats
public class Player {

	private String playerName;
	private int goals;
	private double years; // number of years playing football
	private int numOfGames;
	private double rating; //out of 10

	public Player(String playerName, int goals, double years, int numOfGames, double rating) {
		super();
		this.playerName = playerName;
		this.goals = goals;
		this.years = years;
		this.numOfGames = numOfGames;
		this.rating = rating;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public double getYears() {
		return years;
	}

	public void setYears(double years) {
		this.years = years;
	}

	public int getNumOfGames() {
		return numOfGames;
	}

	public void setNumOfGames(int numOfGames) {
		this.numOfGames = numOfGames;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	public static void setUp(List<Player> players) {
		//goals, double years, int numOfGames, double rating
		players.add(new Player("Ronaldo", 86, 6.7, 120, 9.8));
		players.add(new Player("Messi", 88, 6.4, 114, 9.9));
		players.add(new Player("Neymar", 60, 2.4, 56, 8));
		players.add(new Player("Mbappé", 67, 2, 20, 7));
		players.add(new Player("ibrahimovic", 80, 3,18 , 8.8));

	}

	public static void main(String args[]) {
		List<Player> players = new ArrayList<>();
		setUp(players);
		
		//number of players
		int numberOfPlayers = (int) players.stream().count();
        System.out.println("numberOfPlayers: "+ numberOfPlayers);
        
        //total goals by all Players
        int totalGoals = players.stream().map((Player player) -> player.getGoals()).reduce((result, goal) -> result+=goal).get();
        System.out.println("totalGoals: " + totalGoals);
        
        //highest rating player
        Player highestRatedPlayer = players.stream().max(Comparator.comparing(Player::getRating)).get();
        System.out.println("highestRatedPlayer: "+ highestRatedPlayer.getPlayerName() +" | "+"Rating: " + highestRatedPlayer.rating);
        
        //Lowest rating player
        Player lowestRatedPlayer = players.stream().min(Comparator.comparing(Player::getRating)).get();
        System.out.println("lowestRatedPlayer: "+ lowestRatedPlayer.getPlayerName() +" | "+"Rating: " + lowestRatedPlayer.rating);
        
        
        //Maximum number of goals
        Player highestGoalsByPlayer = players.stream().max(Comparator.comparing(Player::getGoals)).get();
        System.out.println("highestGoalsByPlayer: "+ highestGoalsByPlayer.getPlayerName() +" | "+"Goals: " + highestGoalsByPlayer.goals);
        
        //Minimum number of goals
        Player lowestGoalsByPlayer = players.stream().min(Comparator.comparing(Player::getGoals)).get();
        System.out.println("lowestGoalsByPlayer: "+ lowestGoalsByPlayer.getPlayerName() +" | "+"Goals: " + lowestGoalsByPlayer.goals);

        //Sorting by rating (Ascending)
        List<Player> sortedByRating = players.stream().sorted(Comparator.comparing(Player::getRating)).collect(Collectors.toList());
        System.out.println("Players ordered by rating (Ascending): ");
        sortedByRating.forEach((Player player) -> System.out.println(player.getPlayerName() + " " + player.rating));

        //Sorting by Goals (Descending)
        List<Player> sortedByGoals = players.stream().sorted(Comparator.comparing(Player::getGoals, Comparator.reverseOrder())).collect(Collectors.toList());
        System.out.println("Players ordered by goals (Descending): ");
        sortedByGoals.forEach((Player player) -> System.out.println(player.getPlayerName() + " " + player.goals));

	}

}
