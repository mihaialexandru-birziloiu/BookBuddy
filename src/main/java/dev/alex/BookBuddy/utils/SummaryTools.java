package dev.alex.BookBuddy.utils;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SummaryTools {
    Map<String, String> bookSummariesMap = Map.ofEntries(
            Map.entry("1984",
                    "George Orwell’s novel depicts a dystopian society under total control of the state. "
                            + "People are constantly watched by 'Big Brother,' and free thought is considered a crime. "
                            + "Winston Smith, the main character, struggles to resist this oppressive regime. "
                            + "It is a story about freedom, truth, and ideological manipulation."),

            Map.entry("Brave New World",
                    "Aldous Huxley imagines a futuristic world where technology and genetic control "
                            + "have created an apparently perfect society. "
                            + "Individuals are conditioned from birth to accept their roles. "
                            + "Freedom is sacrificed for stability and artificial pleasure."),

            Map.entry("Fahrenheit 451",
                    "Ray Bradbury describes a society where books are banned and burned by special firemen. "
                            + "The protagonist, Montag, begins to question the system and seeks truth hidden in books. "
                            + "It is a reflection on censorship, ignorance, and the power of knowledge."),

            Map.entry("Animal Farm",
                    "George Orwell creates a political fable where farm animals rebel against humans. "
                            + "The pigs take over leadership but gradually become as corrupt and tyrannical as the humans. "
                            + "It is an allegory about abuse of power and ideological corruption."),

            Map.entry("Lord of the Flies",
                    "William Golding tells the story of boys stranded on a deserted island. "
                            + "Without rules and authority, they descend into savagery and violence. "
                            + "The novel explores human nature and the fragility of civilization."),

            Map.entry("The Catcher in the Rye",
                    "J.D. Salinger tells the story of Holden Caulfield, a rebellious and disillusioned teenager. "
                            + "He struggles with the hypocrisy of the adult world and searches for authenticity and meaning. "
                            + "The novel is an exploration of adolescence and personal identity."),

            Map.entry("To Kill a Mockingbird",
                    "Harper Lee describes the childhood of Scout Finch in a Southern U.S. town marked by racial prejudice. "
                            + "Her father, Atticus Finch, defends a black man wrongly accused of a crime. "
                            + "The book explores themes of justice, compassion, and social inequality."),

            Map.entry("The Great Gatsby",
                    "F. Scott Fitzgerald portrays the Jazz Age through the story of the mysterious Jay Gatsby. "
                            + "His obsession with Daisy Buchanan and desire to recreate the past lead to tragedy. "
                            + "It is a novel about the American Dream, love, and illusion."),

            Map.entry("Crime and Punishment",
                    "Fyodor Dostoevsky explores the moral and psychological dilemmas of Raskolnikov, "
                            + "a young man who commits a murder believing he is justified. "
                            + "Through guilt and eventual punishment, the novel examines conscience and redemption."),

            Map.entry("Pride and Prejudice",
                    "Jane Austen tells the story of Elizabeth Bennet and her complicated relationship with Mr. Darcy. "
                            + "The novel examines social prejudices, the status of women, and the power of true love. "
                            + "It combines social satire with classic romance.")
    );

    @Tool(description = "Get the summary of a book given its title from the dictionary")
    public String getBookSummary(String title) {
        return bookSummariesMap.get(title);
    }

}
