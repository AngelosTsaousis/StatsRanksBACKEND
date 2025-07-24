package gr.alpha.stats.ranks.group;

import org.springframework.stereotype.Service;

@Service
class GroupService {

    private final GroupRepository groupRepository;

    /**
     * Constructor for GroupService.
     *
     * @param groupRepository the repository to manage group data
     */
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    /**
     * Retrieve all groups from the repository.
     *
     * @return
     */
    public Iterable<Group> getAllGroups() {
        return groupRepository.findAll();
    }



}
