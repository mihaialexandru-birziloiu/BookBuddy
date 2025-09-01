export async function getRecommendation(input: string): Promise<string> {
  const response = await fetch("http://localhost:8080/recommendations", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      userInput: input,
    }),
  });

  if (!response.ok) {
    throw new Error("Failed to fetch recommendations");
  }

  const text = await response.text();
  return text;
}
