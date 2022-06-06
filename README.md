# MenuAPI
A very simple and lightweight Menu library made for Spigot API with features like 

# Implementation
To implement this library, you can either clone this repository using ``mvn clone https://github.com/Escapies/MenuAPI`` and put it in your project source code, or follow these commands and steps:

### Step 1
``mvn clone https://github.com/Escapies/MenuAPI``
### Step 2
``mvn clean install``
### Step 3
Go into your ``pom.xml`` and add this:
```xml
    <dependencies>
        <dependency>
            <groupId>io.github.escapies.menuapi</groupId>
            <artifactId>MenuAPI</artifactId>
            <version>1.0-SNAPSHOT</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>
```

# Usage
## Registering the menu handler
You must initialize the ``MenuHandler`` class, usually inside your main class.

## Example

```java
@Override
public void onEnable() {
    new MenuHandler(this)
    // Whatever else you have in your onEnable
    
}
```

## Creating a Menu
To create a menu, you will have to make a class extending the Menu Class, like the example below:

```java
import io.github.escapies.menuapi.Button;
import io.github.escapies.menuapi.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ExampleMenu extends Menu {

    @Override
    public int getSize(Player player) {
        return 9;
    }

    @Override
    public String getTitle(Player player) {
        return "This is a title!";
    }

    @Override
    public boolean isFill(Player player) {
        return false;
    }

    @Override
    public HashMap<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = new HashMap<>();

        buttons.put(4, new ExampleButton());
        return buttons;
    }
}
```

## Creating a button
To create a button, you will have to make a class extending the Button class, like the example below:

```java
import io.github.escapies.menuapi.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.Arrays;
import java.util.List;

public class ExampleButton extends Button {

    @Override
    public String getName() {
        return "This is a Button!";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(
                "This is one line!",
                "This is another line"
        );
    }

    @Override
    public Material getMaterial() {
        return Material.BEDROCK;
    }

    @Override
    public int getAmount() {
        return 1;
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        // put any actions you'd want to do when the button is clicked here
    }
}
```

# Note
If you encounter any issues while using this library, please open an issue and I'll fix it as soon as possible!
