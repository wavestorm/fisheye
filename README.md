# fisheye

This android application provides a very simple means to determine a species of fish at the market place based on a few simple criteria. A user only has to select the type of cut and the apparent colour of the flesh or skin. The user is then presented with a filtered selection of fish cuts as one would expect in the market place. A final selection takes the user to the species information page that displays information on the species, the local common name, an image of the fish, the status of the fish, and a description. The Android "BACK" navigation button is used to jump back to start from the fish cut selection menu to begin another selection. Pressing "BACK" a second time closes the App.

The application has been designed with internationalization in mind through the selection of region and language. Language has been handled internally via the database, rather than relying on Androids internal translation functions. The selection of language also selects the local common name of the species, as common names vary throughout the world. All of the data and images are contained in a structured database called Data.db in the assets directory. The database is accessed as read only directly from the assets directory, which makes installation very simple. The application can also be easily updated by simply updating the database. Data is handled by SQL statements, while Android provides the framework and UI. Everything is internalized and therefore the application requires no android permissions to run.

This application gives the consumer the ability to determine if the fish they wish to purchase is genuine, and not mislabelled. It also empowers the consumer to become better informed on the status of the fish they purchase. With the constant proliferation of illegally caught fish and the sale of unregulated bycatch, there is a growing threat to wild fish populations. By promoting awareness of what they are purchasing, consumers have the power to curb unethical fishing practices and trading by fish traders and fishing enterprises

Content supplied by the MarViva foundation for the hackathon was used. Some content for the colour selection menu was obtained from public domain sources online. All of the artwork used in the cut selection menu was produced by myself during the hackathon and is free for use without restriction. The application icon was created from one of my own underwater photos and is also free for use without restriction.

The application was developed in Android Studio 2.0 for Android version 4.4 upwards.