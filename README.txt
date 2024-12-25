=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: bsinghal
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Inheritance and Subtyping: The superclass is the CardComponent.java class and the
  subclasses are RealCardComponent.java and HoleCardComponent.java. The CardComponent
  class inherits from JPanel and basically creates a rectangle of a standardized width
  and height with a border and a label at the center.

  The HoleCardComponent Class essentially creates the hole card. In the initial set up, the dealer deals themselves a "hole" card
  that is initially face down. When the game ends without black jack or the dealer or
  the user busting, this card is revealed and added into the dealer's hand. As a result,
  the HoleCardComponent creates the "back" of the card, meaning that it sets the
  superclass's background to be red and sets the label's text to be "Hole".

  The RealCardComponent class creates the "front" of the card. It sets the
  superclass's background to be white and sets the label to reveal both the
  card value (ace, king, queen, jack, or 2-10) and the suite. It has 2 additional
  methods that updates the card's label to essentially display a new card with a
  new card value and/or suite.

  This makes use of dynamic dispath when we draw the holeCard. The hole card's component
  is initially an instance of the HoleCardComponent class. However, when the hole card
  is revealed, it then needs to be an instance of the ReadCardComponent class since
  it now displays the card's value and suite. Because of this, the hole card's component
  static type must be of type CardComponent since it's dynamic type can be both
  HoleCardComponent to RealCardComponent.

  2. JUnit Testing: I use JUnit Testing to test out the Current Game components,
  such as making sure that the correct winner was determined and that the
  player's balance, user wins, and dealer wins were updated correctly.
  I also tested to make sure that the game was updated correctly in response
  to different actions, such as the player's hand's score increasing in response
  to a hit and that the game ends, i.e. a result was given in response to a blackjack
  or a bust. I also tested the Deck and the Card, making sure that it did not have duplicate cards
  present and that the random card it generated was valid and that the set of usedCards
  was updated correctly and that exceptions were thrown and handled accordingly if
  the deck was essentially empty, meaning that there were no more cards left to draw
  which caused the game to end.
 I also tested to make sure that the file was updated correctly.


  3. File I/O: I used File I/O to keep track of the game state. It updates the
  state of the game after each round played and displays this to the user every time
  a round ends and user exits.
  I displayed an error window using JOption pane if a file wasn't
  found or an I/O exception occurred.

  4. Collections: To drawing a card from a deck,
  I randomly generated a suite and a number between 1-13.
  To make sure that it doesn't generate the same card as before, I created
  a TreeSet called used that saved all the previously drawn cards. When drawing a card,
  if that card is already in the TreeSet
  I repeatedly drew again until it provided a unique card.

  The reason why I used a TreeSet is that a
  TreeSet does not allow for duplicate objects,
  making it ideal for ensuring no card is drawn more than once. Additionally,
  a TreeSet doesn't have a fixed size, meaning I can add additional cards to it,
  and it provides an easy way to check if this structure contains a specific object.

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named 
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  Card:
  Saves the value of a card, meaning its actual number and its suite.
  Overrides the compare To and the equals method so that two cards with
  the same number and suite are seen as equal.

  Deck:
  Simulates a drawing cards from a deck, meaning that it randomly
  generates a new unique card. This is done every time a player or a dealer hits
  and during the initial set up.

  CardComponent:
  Draws the basic look of the Card: rectangle of standardized width and height
  with black border and label.

  RealCardComponent:
  Draws the "front" of the card, with the suite and card number.
  Generates the label of the card, meaning its number and suite.
  casts from card values of (1, 11, 12, 13) to (Ace, J, Q, K) respectively.
  Allows to update this label to reflect a different card.

  HoleCardComponent:
  Draws the back of the card which is necessary for the dealer's hole
  card which is initially face down.

  Board:
  Draws basic Board of certain width and height and background color

  GameBoard:
  inherits from Board
  Draws the dealer's card, hole card, player's card,
  Displays the buttons "Hit" and "Stand" and updates the
  game correctly when pressed
  Display's the user's balance and their bet.

  GameOverBoard:
  inherits from Board
  Display's the result of that round
  Display's overall player's balance, # of times they won and lost

  InstructionsBoard:
  inherits from Board
  Displays the Instructions for the game

  CurrentGame:
  Determines the result of the round and updates
  balance, user wins and dealer wins
  simulates user hitting and dealer hitting
  Checks if bust or black jack

  PlayerState:
  simulates any player, dealer or user hitting or busting
  Calculates the hands of player

  DealerState:
  inherits from PlayerState, specific class for dealer
  doesn't check for black jack

  UserState:
  inherits from PLayerState, specific class for user
  does check for blackjack because only user can get blackjack

  FileInfoController:
  Reads and Writes data from GameInfoFile

  GameState:
  Gets the GameState and updates the GameState
  CurrentGame gets the state from this class
  Uses FileInfoController

  RunGame:
  updates the frame to display the correct board
  displays the next button when instructions board is displayed
  has a property change listener that listens for when
  the game's result has updated and the game has ended
  It uses a timer to delay for a bit so that the user can see
  what their current card and what the dealer's current card was
  then draws the game over board




- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  It was a bit difficult to figure out how to organize
  all of these different components and classes and what the correct
  inheritance structure should be.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  The private state is encapsulated well because there is no direct way to
  access it, only possible through the get and set methods which were
  mainly used for the JUnit testing.

  I believe that there is a good separation of functionality since each
  class has a very specific task and function in my game.
  If given the chance I think I would change the PlayerState
  organization to be more streamlined since there is not
  much differentiation between the DealerState and UserState.



========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
