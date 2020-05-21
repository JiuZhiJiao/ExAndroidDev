## ExAndroidDev
**Exercises for Android Development**

*This repository includes some exercises of the Android Development.*

**Exercises Checklist:**
- [x] Hello World
- [x] Buttons and onClickListener
- [x] Spinner
- [ ] Recycler View
- [ ] Multiple Activities and Intent
- [ ] Navigation Drawer and Fragments
- [ ] List View
- [ ] Room Persistent Library
- [ ] OKhttp
- [ ] HttpURLConnection
- [ ] Google Custom Search (with Google API)
- [ ] Google Maps
- [ ] Chart
- [ ] Image Downloader
- [ ] Material Design

**Core Code**
Buttons and onClickListener
```java
	//activity_main.xml
	<EditText
	android:id="@+id/edit_message"
	android:layout_width="0dp"
	android:layout_height="wrap_content"
	android:layout_marginStart="16dp"
	android:layout_marginEnd="8dp"
	android:hint="@string/edit_message"
	app:layout_constraintEnd_toStartOf="@+id/reverseButton"
	app:layout_constraintStart_toStartOf="parent"
	tools:layout_editor_absoluteY="16dp" />

	<Button
		android:id="@+id/reverseButton"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:layout_marginEnd="20dp"
		android:text="@string/button_reverse"
		app:layout_constraintEnd_toStartOf="@+id/clearButton"
		tools:layout_editor_absoluteY="13dp" />

	<Button
		android:id="@+id/clearButton"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:layout_marginEnd="16dp"
		android:text="@string/button_clear"
		app:layout_constraintEnd_toEndOf="parent"
		tools:layout_editor_absoluteY="13dp" />
		
	//strings.xml
	<string name="app_name">Reverse Word</string>
	<string name="edit_message">Enter a word</string>
	<string name="button_reverse">Reverse</string>
	<string name="button_clear">Clear</string>
	
	//MainActivity.java within onCreate(...) method
	Button reverseButton = findViewById(R.id.reverseButton);
	Button clearButton = findViewById(R.id.clearButton);

	reverseButton.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			EditText editText = findViewById(R.id.edit_message);
			String builder = new StringBuilder(editText.getText()).reverse().toString();
			editText.setText(builder);
		}
	});

	clearButton.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			EditText editText = findViewById(R.id.edit_message);
			editText.setText("");
		}
	});
```

Spinner
```java
	//strings.xml
	<string name="app_name">SpinnerTutorial</string>
	<string-array name="course_array">
		<item>MIT</item>
		<item>MNS</item>
		<item>MDS</item>
		<item>MBIS</item>
		<item>BIT(Hons)</item>
	</string-array>
	
	//activity_main.xml
	<TextView
		android:id="@+id/textView"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:layout_marginStart="16dp"
		android:layout_marginTop="16dp"
		android:layout_marginEnd="16dp"
		android:text="Hello World!"
		app:layout_constraintEnd_toEndOf="parent"
		app:layout_constraintStart_toStartOf="parent"
		app:layout_constraintTop_toTopOf="parent" />

	<Button
		android:id="@+id/button"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:layout_marginStart="16dp"
		android:layout_marginTop="8dp"
		android:layout_marginEnd="16dp"
		android:text="Button"
		app:layout_constraintEnd_toEndOf="parent"
		app:layout_constraintStart_toStartOf="parent"
		app:layout_constraintTop_toBottomOf="@+id/courseSpinner" />

	<Spinner
		android:id="@+id/courseSpinner"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:layout_marginStart="16dp"
		android:layout_marginTop="8dp"
		android:layout_marginEnd="16dp"
		android:entries="@array/course_array"
		app:layout_constraintEnd_toEndOf="parent"
		app:layout_constraintStart_toStartOf="parent"
		app:layout_constraintTop_toBottomOf="@+id/textView" />

	<Button
		android:id="@+id/button2"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:layout_marginStart="16dp"
		android:layout_marginTop="32dp"
		android:layout_marginEnd="16dp"
		android:text="Add a New Movie"
		app:layout_constraintEnd_toEndOf="parent"
		app:layout_constraintStart_toStartOf="parent"
		app:layout_constraintTop_toBottomOf="@+id/spinner2" />

	<Spinner
		android:id="@+id/spinner2"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:layout_marginStart="16dp"
		android:layout_marginTop="16dp"
		android:layout_marginEnd="16dp"
		app:layout_constraintEnd_toEndOf="parent"
		app:layout_constraintStart_toStartOf="parent"
		app:layout_constraintTop_toBottomOf="@+id/editText" />

	<EditText
		android:id="@+id/editText"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:layout_marginStart="16dp"
		android:layout_marginTop="150dp"
		android:layout_marginEnd="16dp"
		android:ems="10"
		android:inputType="textPersonName"
		android:text="Input New Movie Name"
		app:layout_constraintEnd_toEndOf="parent"
		app:layout_constraintStart_toStartOf="parent"
		app:layout_constraintTop_toBottomOf="@+id/button" />
	
	//MainActivity.java within onCreate(...) method
	final TextView textView = findViewById(R.id.textView);
	Button button = findViewById(R.id.button);
	final Spinner courseSpinner = findViewById(R.id.courseSpinner);

	button.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			String selectedCourse = courseSpinner.getSelectedItem().toString();
			textView.setText(selectedCourse);
		}
	});

	// Dynamic data using ArrayAdapter
	List<String> list = new ArrayList<String>();
	list.add("Avengers");
	list.add("Lion King");
	list.add("John Wick");

	Button buttonAddMovie = findViewById(R.id.button2);
	final EditText editText = findViewById(R.id.editText);
	final Spinner movieList = findViewById(R.id.spinner2);

	final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
	movieList.setAdapter(spinnerAdapter);

	buttonAddMovie.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			String newMovie = editText.getText().toString();
			spinnerAdapter.add(newMovie);
			spinnerAdapter.notifyDataSetChanged();
			movieList.setSelection(spinnerAdapter.getPosition(newMovie));
		}
	});

	movieList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			String selectedMovie = parent.getItemAtPosition(position).toString();
			if (selectedMovie != null) {
				Toast.makeText(parent.getContext(), "Movie selected is " + selectedMovie, Toast.LENGTH_LONG).show();
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {

		}
	});
```

Recycler View
```java

```

Multiple Activities and Intent
```java

```

Navigation Drawer and Fragments
```java

```

List View
```java

```

Room Persistent Library
```java

```

OKhttp
```java

```

HttpURLConnection
```java

```

Google Custom Search (with Google API)
```java

```

Google Maps
```java

```

Chart
```java

```

Image Downloader
```java

```