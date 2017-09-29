package com.sii.eventfull.web.rest;

import com.sii.eventfull.repository.EventRepository;
import com.sii.eventfull.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

/**
 * REST controller for managing events.
 * <p>
 * This class accesses the Event entity, and needs to fetch its collection of authorities.
 * <p>
 * For a normal use-case, it would be better to have an eager relationship between Event and Authority,
 * and send everything to the client side: there would be no View Model and DTO, a lot less code, and an outer-join
 * which would be good for performance.
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the event and the authorities, because people will
 * quite often do relationships with the event, and we don't want them to get the authorities all
 * the time for nothing (for performance reasons). This is the #1 goal: we should not impact our events'
 * application because of this use-case.</li>
 * <li> Not having an outer join causes n+1 requests to the database. This is not a real issue as
 * we have by default a second-level cache. This means on the first HTTP call we do the n+1 requests,
 * but then all authorities come from the cache, so in fact it's much better than doing an outer join
 * (which will get lots of data from the database, for each HTTP call).</li>
 * <li> As this manages events, for security reasons, we'd rather have a DTO layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this case.
 */
@RestController
@RequestMapping("/api")
public class EventResource {

    private final Logger log = LoggerFactory.getLogger(EventResource.class);

    private static final String ENTITY_NAME = "eventManagement";

    private final EventRepository eventRepository;

    private final MailService mailService;

    public EventResource(EventRepository eventRepository, MailService mailService) {

        this.eventRepository = eventRepository;
        this.mailService = mailService;
    }
/*
    *//**
     * POST  /events  : Creates a new event.
     * <p>
     * Creates a new event if the login and email are not already used, and sends an
     * mail with an activation link.
     * The event needs to be activated on creation.
     *
     * @param managedEventVM the event to create
     * @return the ResponseEntity with status 201 (Created) and with body the new event, or with status 400 (Bad Request) if the login or email is already in use
     * @throws URISyntaxException if the Location URI syntax is incorrect
     *//*
    @PostMapping("/events")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity createEvent(@Valid @RequestBody ManagedEventVM managedEventVM) throws URISyntaxException {
        log.debug("REST request to save Event : {}", managedEventVM);

        if (managedEventVM.getId() != null) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new event cannot already have an ID"))
                .body(null);
            // Lowercase the event login before comparing with database
        } else if (eventRepository.findOneByLogin(managedEventVM.getLogin().toLowerCase()).isPresent()) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "eventexists", "Login already in use"))
                .body(null);
        } else if (eventRepository.findOneByEmail(managedEventVM.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "emailexists", "Email already in use"))
                .body(null);
        } else {
            Event newEvent = eventService.createEvent(managedEventVM);
            mailService.sendCreationEmail(newEvent);
            return ResponseEntity.created(new URI("/api/events/" + newEvent.getLogin()))
                .headers(HeaderUtil.createAlert( "A event is created with identifier " + newEvent.getLogin(), newEvent.getLogin()))
                .body(newEvent);
        }
    }

    *//**
     * PUT  /events : Updates an existing Event.
     *
     * @param managedEventVM the event to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated event,
     * or with status 400 (Bad Request) if the login or email is already in use,
     * or with status 500 (Internal Server Error) if the event couldn't be updated
     *//*
    @PutMapping("/events")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<EventDTO> updateEvent(@Valid @RequestBody ManagedEventVM managedEventVM) {
        log.debug("REST request to update Event : {}", managedEventVM);
        Optional<Event> existingEvent = eventRepository.findOneByEmail(managedEventVM.getEmail());
        if (existingEvent.isPresent() && (!existingEvent.get().getId().equals(managedEventVM.getId()))) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "emailexists", "Email already in use")).body(null);
        }
        existingEvent = eventRepository.findOneByLogin(managedEventVM.getLogin().toLowerCase());
        if (existingEvent.isPresent() && (!existingEvent.get().getId().equals(managedEventVM.getId()))) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "eventexists", "Login already in use")).body(null);
        }
        Optional<EventDTO> updatedEvent = eventService.updateEvent(managedEventVM);

        return ResponseUtil.wrapOrNotFound(updatedEvent,
            HeaderUtil.createAlert("A event is updated with identifier " + managedEventVM.getLogin(), managedEventVM.getLogin()));
    }

    *//**
     * GET  /events : get all events.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and with body all events
     *//*
    @GetMapping("/events")
    @Timed
    public ResponseEntity<List<EventDTO>> getAllEvents(@ApiParam Pageable pageable) {
        final Page<EventDTO> page = eventService.getAllManagedEvents(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/events");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    *//**
     * @return a string list of the all of the roles
     *//*
    @GetMapping("/events/authorities")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public List<String> getAuthorities() {
        return eventService.getAuthorities();
    }

    *//**
     * GET  /events/:login : get the "login" event.
     *
     * @param login the login of the event to find
     * @return the ResponseEntity with status 200 (OK) and with body the "login" event, or with status 404 (Not Found)
     *//*
    @GetMapping("/events/{login:" + Constants.LOGIN_REGEX + "}")
    @Timed
    public ResponseEntity<EventDTO> getEvent(@PathVariable String login) {
        log.debug("REST request to get Event : {}", login);
        return ResponseUtil.wrapOrNotFound(
            eventService.getEventWithAuthoritiesByLogin(login)
                .map(EventDTO::new));
    }

    *//**
     * DELETE /events/:login : delete the "login" Event.
     *
     * @param login the login of the event to delete
     * @return the ResponseEntity with status 200 (OK)
     *//*
    @DeleteMapping("/events/{login:" + Constants.LOGIN_REGEX + "}")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> deleteEvent(@PathVariable String login) {
        log.debug("REST request to delete Event: {}", login);
        eventService.deleteEvent(login);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert( "A event is deleted with identifier " + login, login)).build();
    }*/
}
