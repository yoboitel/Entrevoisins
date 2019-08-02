package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    private List<Neighbour> fav_neighbours = DummyNeighbourGenerator.generateFavNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getFav_neighbours() {
        return fav_neighbours;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void deleteFavNeighbour(Neighbour neighbour) { fav_neighbours.remove(neighbour);}
}
