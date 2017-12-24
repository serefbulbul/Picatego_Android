# Picatego_Android

Picatego simply has predefined 6 categories from Flickr API.

Categrories:

* Motocycle
* Nature
* Camping
* Snowboard
* Wakeboard
* Interrail

After any of the category selected, it lists the categories according to interest weights.

# Architecture

There are 3 main layers which are:

* View
* Presenter
* Interactor

*View*

    View layer responsible from UI components. It communicates with presenter layer and get the 
    required data from it. In addition, it notifies presenter layer about user interaction
    interactions like button clicks.
  
    View layer contains Context class from Android framework. Therefore, writing unit tests for 
    view layer is not possible without running Android framework. There is a helper framework to 
    run Android framework virtully, named, Robolectric, but it did not be used in this project. 

*Interactor*
    
    Interactor layer mainly keeps the required data and interact with external services like
    network and database services. 

*Presenter*

    Presenter layer contains the bussiness logic. It gets the requireid data from interactor layer 
    and transfers it to the view layer. It is normal standard Java class so it is approproate for 
    unit tests.
    
*Template*

    Since there are many classes to be created for only one feature. I create a VIP Template to
    generate those classes automatically. Which can be downloaded from here:
    
    https://goo.gl/TdUY6c
    
    
# Used Technologies

* RxJava 2
* Retrofit 2
* Dagger 2
* ButterKnife
* RxBinding
* Picasso
* Mockito