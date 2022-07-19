package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.EmptyInputException;
import com.example.model.Movie;
import com.example.model.Relation;
import com.example.model.Seat;
import com.example.model.Ticket;
import com.example.model.User;
import com.example.services.MovieImplementation;
import com.example.services.RelationImplementation;
import com.example.services.SeatImplementation;
import com.example.services.TicketImplementation;
import com.example.services.UserImplementation;

@RestController
public class UserController {
	Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserImplementation ui;
	@Autowired
	private MovieImplementation mi;
	@Autowired
	private RelationImplementation ri;
	@Autowired
	private SeatImplementation si;
	@Autowired
	private TicketImplementation ti;

	@PostMapping("login")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		logger.info("-----------------------User being created-------------------");

		User u = ui.addUser(user);
		logger.info("----------------------User created------------------------");
		return new ResponseEntity<User>(u, HttpStatus.CREATED);
	}

	@GetMapping("users")
	public List<User> findAllUser() {
		logger.info("-----------------------User list printed-------------------");
		return ui.findAllUsers();
	}

	@GetMapping("userlogin/{user}/{pass}")
	public ResponseEntity<User> loginUser(@PathVariable("user") String userId, @PathVariable("pass") String pass,
			HttpServletRequest req) {
		User u = ui.getUserByUserId(userId);
		if (u.getPassword().equalsIgnoreCase(pass)) {
			String k = userId + "1";
			req.getSession().setAttribute(k, u);
			logger.info("-----------------------User login successfull-------------------");
			return new ResponseEntity<User>(u, HttpStatus.ACCEPTED);
		}
		throw new EmptyInputException("611", "Wrong userid or password!");
	}

	@GetMapping("{user}")
	public List<Movie> searchInMovies(@PathVariable("user") String user, HttpServletRequest req) {
		User u = (User) req.getSession().getAttribute(user + "1");
		if (u != null) {
			logger.info("-----------------------movie list Printed for User-------------------");
			return mi.getMovies();
		}
		throw new EmptyInputException("612", "UserId does not exist!");
	}

	@GetMapping("{user}/{movie}")
	public List<Relation> searchIncinemas(@PathVariable("user") String user, @PathVariable("movie") String movie,
			HttpServletRequest req) {
		User u = (User) req.getSession().getAttribute(user + "1");
		Movie m = mi.getMovieById(movie);
		if (u != null) {
			if (m != null) {
				logger.info("-----------------------All Shows Printed-------------------");
				return ri.findByMovie(m);
			} else
				throw new EmptyInputException("614", "Show not Available for this movie");
		}
		throw new EmptyInputException("613", "UserId does not exist ");
	}

	@GetMapping("{user}/relation/{rid}")
	public ResponseEntity<Seat> searchforSeats(@PathVariable("user") String user, @PathVariable("rid") String rid,
			HttpServletRequest req) {
		User u = (User) req.getSession().getAttribute(user + "1");
		Relation r = ri.findById(rid);
		if (u != null) {
			if (r != null) {
				Seat seat = si.findSeatByRelationId(r);
				logger.info("-----------------------Check out for seats-------------------");
				return new ResponseEntity<Seat>(seat, HttpStatus.OK);
			} else
				throw new EmptyInputException("614", "Seat not Available for this movie");
		}
		throw new EmptyInputException("613", "UserId does not exist ");
	}

	@PostMapping("{user}/bookticket/{sid}/{seatcount}")
	public ResponseEntity<User> bookTickets(@PathVariable("user") String user, @PathVariable("sid") String sid,
			@PathVariable("seatcount") int seatcount, HttpServletRequest req) {
		User u = (User) req.getSession().getAttribute(user + "1");
		Seat s = si.findSeatById(sid);

		if (u != null) {
			if (s != null) {
				Ticket ticket = new Ticket();
				ticket.setSeatsBooked(seatcount);
				ticket.setSeat(s);
				ticket.setUser(u);
				ticket.setTicketPrice(seatcount * 150);
				ti.createTicket(ticket);
				u.getTicket().add(ticket);
				si.bookSeats(s, seatcount);
				logger.info("-----------------------TICKET CREATED-------------------");
				return new ResponseEntity<User>(u, HttpStatus.CREATED);
			} else
				throw new EmptyInputException("614", "Seat not Available for this movie");
		}
		throw new EmptyInputException("613", "UserId does not exist ");
	}
	@PutMapping("{user}/cancelticket/{tid}")
	public ResponseEntity<User> cancelTickets(@PathVariable("user") String user,@PathVariable("tid") String tid, HttpServletRequest req){
		User u = (User) req.getSession().getAttribute(user+"1");
		Ticket t= ti.findTicketById(tid);
		if(u!=null) {
			if(t!=null)
			{
				    Seat s =t.getSeat();
				    int seatcount=s.getSeatsTaken();		   
				    
				    s.setSeatsTaken(seatcount-t.getSeatsBooked());
				    si.updateSeat(s);
				    t.setSeatsBooked(0);
				    t.setTicketPrice(0);
				    ti.updateTicket(t);
					u.getTicket().remove(t);
					User u1 = ui.updateUser(u);
					logger.info("-----------------------TICKET CANCELLED-------------------");
					return new ResponseEntity<User>(u1,HttpStatus.CREATED);
				}
			else 
				throw new EmptyInputException("616", "Ticket not Available for this user");
		}
		throw new EmptyInputException("613", "UserId does not exist ");
	}
	
}
