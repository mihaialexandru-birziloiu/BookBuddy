import React, { useState } from "react";
import InputSection from "./components/InputSection";
import OutputSection from "./components/OutputSection";
import { getRecommendation } from "./api/recommendations";

function App() {
  const [query, setQuery] = useState("");
  const [recommendations, setRecommendations] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    try {
      const response = await getRecommendation(query);
      setRecommendations(response);
    } catch (error) {
      console.error("Error fetching recommendations:", error);
      setRecommendations("Failed to fetch recommendations. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-100 via-purple-100 to-pink-100 flex items-center justify-center p-6 font-sans">
      <div className="bg-white shadow-2xl border-2 border-indigo-500 rounded-3xl p-8 w-full max-w-7xl flex gap-8">
        <InputSection
          query={query}
          setQuery={setQuery}
          loading={loading}
          handleSubmit={handleSubmit}
        />
        <OutputSection
          recommendations={recommendations}
          hasRecommendations={!!recommendations}
        />
      </div>
    </div>
  );
}

export default App;
