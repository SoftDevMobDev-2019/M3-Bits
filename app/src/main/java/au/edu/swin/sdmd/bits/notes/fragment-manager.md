# Using fragments

The process is (this is altered slightly from the slides):

1. Add a frame layout to the layout file and give it an ID -- this is our container.
2. Create an instance of the Fragment’s class.
3. Pass any additional intent arguments through to the class.
4. Obtain a reference to the SupportFragmentManager instance.
5. Call the beginTransaction() method on the FragmentManager instance. This returns a FragmentTransaction instance.
6. Call the add() method of the fragment transaction instance, and pass:
  * the resource id of the container (Activity) view 
  * the Fragment class instance.
7. Call the commit() method of the Fragment Transaction.

This works! Well, kind of. If you convert a few numbers* you'll note that the fragment is not 
behaving properly. The line

```java
fm.beginTransaction().add(R.id.fragment_output, fBinary).commit();
```

needs to become

```java
fm.beginTransaction().replace(R.id.fragment_output, fBinary).commit();
```

*Always click on buttons a few times to test; don't just assume your app is working on the first click.
Top tip there. Interestingly this is picked up by Espresso; it only sees the first fragment and not 
the overlays.

One last fun thing to do is play with the back stack; you will recall that activities go on a stack
and we can pop them by pressing the back button. Fragments by default don't go on the back stack
(again this can be seen by converting a few numbers then pressing back -- you should simply exit the
application). However if you do want fragments to go onto the back stack then you'll need to call 
addToBackStack(null):

```java
fm.beginTransaction().replace(R.id.fragment_output, fBinary).addToBackStack(null).commit();
```