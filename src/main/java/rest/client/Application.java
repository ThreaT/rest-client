package rest.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rest.client.controller.ImdbController;
import rest.client.controller.ItunesController;
import rest.client.view.ResultsView;

public class Application {

    public static void main(String args[]) {
        if (valid(args)) {
            /**
             * Get movie or album based on input arguments
             */
            ApplicationContext context = new AnnotationConfigApplicationContext("rest.client");
            String type = args[0];
            String term = args[1];
            if (type.equalsIgnoreCase("movie")) {
                ImdbController imdbController = context.getBean(ImdbController.class);
                ResultsView imdbMovie = imdbController.search(term);
                System.out.println(imdbMovie.print(true));
            } else if (type.equalsIgnoreCase("album")) {
                ItunesController itunesController = context.getBean(ItunesController.class);
                ResultsView itunesAlbum = itunesController.search(term);
                System.out.println(itunesAlbum.print(true));
            }
        }
    }

    public static Boolean valid(String args[]) {
        /**
         * Get type from input arguments, log error if not provided or invalid
         */
        if ((args.length < 1) || (args[0] == null) || (args[0].isEmpty()) || ((!args[0].equalsIgnoreCase("movie")) && (!args[0].equalsIgnoreCase("album")))) {
            ResultsView errorResultsView = new ResultsView("Error, invalid type, please choose one of <movie>|<album>");
            System.out.println(errorResultsView.print(false));
            return Boolean.FALSE;
        }

        /**
         * Get search term from input arguments, log error if not provided or
         * invalid
         */
        if ((args.length < 2) || (args[1] == null) || (args[1].isEmpty()) || (args[1].length() < 3)) {
            ResultsView errorResultsView = new ResultsView("Error, invalid search term, please ensure search term length is at least 3 characters");
            System.out.println(errorResultsView.print(false));
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

}
