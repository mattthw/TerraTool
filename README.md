# GraffitiTools
Learning the Spigot API

Created a basic brush with customizable options for distance, color, and size.

The Bukkit/Spigot API allows this to reach large distances within reason to the viewers line of sight. The brush is in the shape of a ball. It loops through the blocks in a cube based on the desired size, storing all which fall within a radius from the current block to the originally targeted block. Colors are customizable to an extent. It would be pretty simple to allow it to simply take an input for the block id number as a paint color, and even a block id to replace.

Commands:
```
/graffiti (alias for 'graffititools'. Shorter: 'gt') -dplay current brush status/settings
/graffiti on - enable brush
/graffiti off - disable brush
/graffiti can - receive object needed to make terrain/block alterations/painting
/graffiti size <size> - set radius from size 1 to 10. (Size restriction is artificial and could be made configurable)
/graffiti dist <dist> - distance in blocks from 1 to 100. (a brush of dist 100 with size 10 would edit a block up to 110 blocks away)
/graffiti <color> - change brush color
```
#TODO
- Brushes:
  - ball brush
  - square brush
  - point brush
- Tools:
  - Designate different brushes for fill replace and delete
  - fill tool
  - replace tool
  - hollow tool
  - draw tool
    - with interpolation option for best fit line. This should help allow users to draw a clean line without missing blocks due to the client target landing on a different targetblock faster than the server can keep track
- Clean up command system
  - more advanced argument implementation pls.
  - ex: '/gt set nmid wool:red'
  - ex: '/gt set tool fill'
  - ex: '/gt set interpolate on'
  - ex: '/gt help', '/gt tool help', '/gt brush help'
- Record 'x' amount of changes
- Implement undo using that record of changes
- Better help system
