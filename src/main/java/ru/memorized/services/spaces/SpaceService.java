package ru.memorized.services.spaces;

import java.util.List;

import ru.memorized.data.entity.Space;

public interface SpaceService {

    public List<Space> getSpaces();

    public Space getSpace(int spaceId);

    public Space getSpace(String title);

}
