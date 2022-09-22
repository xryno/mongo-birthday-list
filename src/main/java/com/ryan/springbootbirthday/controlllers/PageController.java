package com.ryan.springbootbirthday.controlllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ryan.springbootbirthday.models.BirthdayItem;
import com.ryan.springbootbirthday.repositories.BirthdayItemRepo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Controller

public class PageController {

    @Autowired
    private BirthdayItemRepo birthdayItemRepo;

    @GetMapping("/u")
    public List<BirthdayItem> getTodos(){

        List allUpdate = birthdayItemRepo.findAll();

      for (int i = 0; i<allUpdate.size(); i++) {
        ((BirthdayItem) allUpdate.get(i)).setCurrentAge(Period.between(LocalDate.parse(((BirthdayItem) allUpdate.get(i)).getDob()), LocalDate.now()).getYears());
        birthdayItemRepo.save((BirthdayItem) allUpdate.get(i));
      }
        
        return birthdayItemRepo.findAll();
    }

//read

    @GetMapping("/")
public ModelAndView getHome(Model model) {
//update age on load

int year = Year.now().getValue();

List allUpdate = birthdayItemRepo.findAll();

for (int i = 0; i<allUpdate.size(); i++) {
  ((BirthdayItem) allUpdate.get(i)).setCurrentAge(Period.between(LocalDate.parse(((BirthdayItem) allUpdate.get(i)).getDob()), LocalDate.now()).getYears());
  birthdayItemRepo.save((BirthdayItem) allUpdate.get(i));

  LocalDate dob = LocalDate.parse(((BirthdayItem) allUpdate.get(i)).getDob());
  LocalDate orignalBirthDate = LocalDate.of(dob.getYear(), dob.getMonthValue(), dob.getDayOfMonth());
    LocalDate today = LocalDate.now();

LocalDate nextBirthDate = orignalBirthDate.withYear(year);
if (nextBirthDate.isBefore(today)) {
    nextBirthDate = nextBirthDate.plusYears(1);
}
long daysBetween = ChronoUnit.DAYS.between(today, nextBirthDate);
((BirthdayItem) allUpdate.get(i)).setDaysLeft(daysBetween);
    birthdayItemRepo.save((BirthdayItem) allUpdate.get(i));

// System.out.println("Next birthday = " + DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(nextBirthDate));
}

//for html page
        Iterable<BirthdayItem> birthdayItems = birthdayItemRepo.findAll();
        model.addAttribute("birthdayItems", birthdayItems);
         return new ModelAndView("welcome.html");
}


//create

@PostMapping("/")
    public String addTodoItem(BirthdayItem body) {

    BirthdayItem birthdayItem = new BirthdayItem();
    birthdayItem.setName(body.getName());
    birthdayItem.setDob(body.getDob());
    birthdayItem.setCurrentAge(Period.between(LocalDate.parse(body.getDob()), LocalDate.now()).getYears());
    birthdayItem.setNewAge((Period.between(LocalDate.parse(body.getDob()), LocalDate.now()).getYears()) +1);
    birthdayItem.setDaysLeft(body.getDaysLeft());
    birthdayItem.setDateModified(Instant.now());
    birthdayItem.setDateCreated(Instant.now());
    birthdayItemRepo.save(birthdayItem);
    return "redirect:/";
}


//update
@PostMapping("/update/{id}")
public String updateBirthdayItem(@PathVariable("id") String id,BirthdayItem body) {

    BirthdayItem birthdayItem = new BirthdayItem();

    birthdayItem.setId(id);
    birthdayItem.setName(body.getName());
    birthdayItem.setDob(body.getDob());
    birthdayItem.setDateModified(Instant.now());
    birthdayItemRepo.save(birthdayItem);
    return "redirect:/";
}
//delete
@GetMapping("/todo/{id}")
public String deleteTodoItem(@PathVariable("id") String id) {

    BirthdayItem birthdayItem = new BirthdayItem();
    birthdayItem.setId(id);
    birthdayItemRepo.delete(birthdayItem);
    return "redirect:/";
}
     
}
