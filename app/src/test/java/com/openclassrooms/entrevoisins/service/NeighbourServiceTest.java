package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }



    @Test
    public void getFavsNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getFav_neighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.FAV_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void addNeighbourToFavsWithSuccess() {
        //Voisin de test
        Neighbour favVoisin = new Neighbour(1, "Yohan", "http://i.pravatar.cc/150?u=a042581f4e29026704e");

        //Recupere la liste des favoris, elle est vide donc sa taille est de 0
        List<Neighbour> favsneighbour = service.getFav_neighbours();

        //On ajoute un voisin à la liste des favoris, sa taille et donc maintenant de 1
        favsneighbour.add(favVoisin);

        //Si la taille de la liste est différent de 0, c'est que le favoris a bien été enregistré dans la liste des favs
        assertThat(favsneighbour.size(), is(1));
    }
}
