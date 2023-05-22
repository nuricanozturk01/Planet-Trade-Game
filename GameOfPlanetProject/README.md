## Planet Trade Game Project
### Planet Trade Game
Planet Trade Game is a turn-based multiplayer game. Each players can buy item, sell item, buy fuel, change spaceship, buy spaceship and travel to another planets.

The game universe consists of the following elements:

 -  A black hole, which explodes and creates a galaxy.
 -  A galaxy, which contains a set of planets located at specific distances from each other in terms of light-years.
 -  Each planet features a market where commodities are bought and sold.
 -  A commodity has a name, unit volume, and decay ratio.
 -  Each supply of a commodity at a market has a current supply amount, unit buy price, and unit sell price.
 -  A player has a name, current money, spaceship, and current planet.
 -  A planet has a name, a market, unit fuel price, and spaceship parking price per turn.
 -  A spaceship has a name, buy price, a list of cargo, volume capacity, speed in terms of light-years per turn, current fuel, fuel capacity, and fuel usage per light-year.
 -  A cargo contains a commodity and its quantity.
#### Each turn players can following actions:
 -  Buy new market items from the market of the currentplanet as much as the capacity
of the spaceship and the supply of the market allows. The buy operation causes the current money drop with the amount calculated by unit buy price of the market item in the market and the amount
 -  Sell any cargo in the spaceship. The sell operation causes increase in the current money with amount calculated by the cargo amount and unit sell price of the commodity in the market.
 -  Buy fuel as much as the fuel capacity of the spaceship allows. It causes the current Money drop with the amount calculated by the unit fuel price at the current planet
 -  Plan journey to another planet. If this is done in one turn the player will be at the target planet in the next turn if the spaceship has sufficient amount of fuel which is calculated by the fuel usage of the spaceship and the distance between the planets. Otherwise the player stays at the same planet causing a drop in the current money by the parking price of the current planet.
#### Each turn game should update itself: 
 - At each turn player actions are validated by the game and if they are valid necessary updates are performed accordingly which includes: o the current money of each player
    -  current planet of each player
 -  The current cargo of each player are decayed.
 -  At each market of each planet the following changes randomly
    -  unit buy price and unit sell price of each market item
    -  current supply of each market item (with relatively small amount