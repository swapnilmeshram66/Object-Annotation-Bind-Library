# Object-Annotation-Bind-Library
This library will help you to bind empty objects directly inside the constructor to avoid null pointers.

## Installation

Put following lines in your project level build.gradle .

```bash
allprojects {
	repositories {
	...
	maven { url 'https://jitpack.io' }
	}
}
```

And in your app level build.gradle

```bash
dependencies {
      implementation 'com.github.swapnilmeshram66:Object-Annotation-Bind-Library:0.1.0'
}
```

## Usage

```Java

public class MyDemo
{

  @BindInt
  private ObservableInt myInt;
  ...

  public MyDemo(){
    ObjectBinder.bind(this);
  }
...
}

```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

