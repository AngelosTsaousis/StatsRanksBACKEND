package gr.alpha.stats.ranks.group;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/groups")
class GroupController {

    private final GroupService groupService;

    /**
     * Constructor for GroupController.
     *
     * @param groupService the service to manage group data
     */
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    /**
     * Retrieve all groups.
     *
     * @return an iterable of all groups
     */
     @GetMapping
     public Iterable<Group> getAllGroups() {
         return groupService.getAllGroups();
     }
}
