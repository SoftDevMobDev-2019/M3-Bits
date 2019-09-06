# Communication between fragments

(This is working off the one fragment version.)

Sometimes we want to communicate between two fragments. In the previous version of the binary 
converter we had a single fragment just showing the answer, within an activity that was handling 
the input. In this version we'll make the input a fragment and use the main activity as a host only,
as well as the mediator between the two fragments. Recall fragments do not communicate with each 
other directly.

1. Create a new fragment -- this is easiest done using the IDE menu, however note I unchecked factory 
methods (we do need callbacks though).
2. Move the button and the text input into the layout for the new fragment. I wrapped them in a
LinearLayout for simplicity. 
3. Added the new fragment holder to the activity_main layout and fixed the constraints.
4. Amended the parameter of onFragmentInteraction in the new fragment to be a String rather than 
a Uri, as it says in the TODO. This will allow us to pass the string from the input text.

```java
public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(String s);
}
```

Change onButtonPressed() too to take a String.

5. Refactor MainActivity:
  * implement OnFragmentInteractionListener
  * remove the references to the button and input views
  * add the new fragment
  
```java
Fragment fInput = new InputFragment();
FragmentManager fm = getSupportFragmentManager();
fm.beginTransaction().add(R.id.fragment_input, fInput).commit();
``` 

  * move the contents of the click listener to the new onFragmentInteraction method. Update the 
  variable that is holding the input string as well.
  * Update the output fragment call to be replace instead of add.
  
6. You can run at this point, but it won't show the output yet. This is because we've not told the 
new fragment to communicate the output to the original fragment. 

In the new fragment, we need to refine the onCreateView method as follows to do this; it's 
basically moving the click event from MainActivity to the fragment.

```java
        View v = inflater.inflate(R.layout.fragment_input, container, false);

        final EditText etDecimal = v.findViewById(R.id.etDecimal);
        Button convert = v.findViewById(R.id.button);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(etDecimal.getText().toString());
            }
        });

        return v;
```

And that should now run better. 

It could be refined further by adding the output fragment on opening the app but at least the idea 
of how to communicate from one fragment to another should be evident. If you want to confirm that 
you have two fragments on screen, you can change the background colour of the fragments by adding

```xml
android:background="#CDDC39"

```

to the FrameLayout.

Keep in mind that the "talking" fragment needs to define an interface with a method which takes 
the data as an argument, the activity in the middle needs to implement the interface and load the 
data into a bundle, which the "listening" fragment then processes.


