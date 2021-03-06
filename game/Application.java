package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.*;
import game.ground.*;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {
	/**
	 * Responsible to create dirt, wall, floor, tree, vending machine
	 */
	public static void main(String[] args) {
		ArrayList<List<String>> mapsContainer = new ArrayList<>();

		FancyGroundFactory groundFactory = new FancyGroundFactory(new VendingMachine(),new Bush(),new Dirt(), new Wall(), new Floor(), new Tree(), new Lake());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		".....#######...........................,........................................",
		".....#_____#......~...........................................~.................",
		".....#H____#.......................,............................................",
		".....###.###....................................................................",
		"................................................................................",
		"......................................+++.......................................",
		"...........,...........................++++...........................,.........",
		"...................................+++++........................................",
		".....................................++++++.....................................",
		"..........................~..........++++...........~...........................",
		"....................................+++++................................,......",
		".......................................++.......................................",
		"............+++.................................................................",
		".............+++++..............................................................",
		"...............++...........,............................+++++..........,.......",
		".............+++...............,....................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		"..................~...........................................~..........++.....",
		"..................................,.....................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		List<String> map2 = Arrays.asList(
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"......................................+++.......................................",
				".......................................++++.....................................",
				"...................................+++++........................................",
				".....................................++++++.....................................",
				"......................................+++.......................................",
				".....................................+++........................................",
				"................................................................................",
				"............+++.................................................................",
				".............+++++..............................................................",
				"...............++........................................+++++..................",
				".............+++....................................++++++++....................",
				"............+++.......................................+++.......................",
				"................................................................................",
				".........................................................................++.....",
				"........................................................................++.++...",
				".........................................................................++++...",
				"..........................................................................++....",
				"................................................................................");
		mapsContainer.add(map);
		mapsContainer.add(map2);

		World world = new JurassicWorld(new Display(),mapsContainer,groundFactory);
		world.run();
	}
}
