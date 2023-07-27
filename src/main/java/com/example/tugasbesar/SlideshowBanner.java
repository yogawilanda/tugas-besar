package com.example.tugasbesar;

public class SlideshowBanner {

//	@FXML
//	private void initialize() {
//		// Set the movieBackdrop to be visible
//		movieBackdrop.setVisible(true);
//
//		// Initialize the backdropImageView with the first image
//		movieBackdrop.setImage(backDropLoader(0));
//
//		// Create a Timeline for the slideshow
//		Duration slideDuration = Duration.seconds(7); // Adjust the interval between images (in seconds)
//		slideshowTimeline = new Timeline(new KeyFrame(slideDuration, event -> showNextBackdrop()));
//		slideshowTimeline.setCycleCount(Timeline.INDEFINITE);
//
//		// Start the slideshow in a separate thread
//		slideshowThread = new Thread(() -> {
//			try {
//
//				while (!stopSlideshow) {
//					// Rest of your slideshow code...
//					// Run the slideshow on the JavaFX Application Thread to avoid concurrency issues
//					Platform.runLater(() -> {
//						// Set up the fade-in transition
//						FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), movieBackdrop);
//						fadeIn.setFromValue(0);
//						fadeIn.setToValue(1);
//						fadeIn.setOnFinished(e -> {
//							// Wait for a short duration after the fade-in animation
//							try {
//								Thread.sleep(3000);
//							} catch (InterruptedException ex) {
//								ex.printStackTrace();
//							}
//							// Set up the fade-out transition
//							FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), movieBackdrop);
//							fadeOut.setFromValue(1);
//							fadeOut.setToValue(0);
//							fadeOut.play();
//						});
//						fadeIn.play();
//					});
//
//					// Wait for the slideDuration before showing the next backdrop
//					Thread.sleep((long) slideDuration.toMillis());
//					showNextBackdrop();
//				}
//			} catch (InterruptedException ex) {
//				System.out.println( "Slideshow stopped");
//			}
//		});
//
//		// Start the slideshow thread
//		slideshowThread.setDaemon(true); // Mark the thread as a daemon thread to automatically terminate it when the application exits
//		slideshowThread.start();
//	}


//	private void showNextBackdrop() {
//		if (filesBackdrop.length > 0) {
//			currentBackdropIndex = (currentBackdropIndex + 1) % filesBackdrop.length;
//			movieBackdrop.setImage(backDropLoader(currentBackdropIndex));
//		}
//	}
//
//	private void stopSlideshow() {
//		stopSlideshow = true;
//		slideshowThread.interrupt();
//	}
}
