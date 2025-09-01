import React from "react";

interface OutputSectionProps {
  recommendations: string;
  hasRecommendations: boolean;
}

const OutputSection: React.FC<OutputSectionProps> = ({
  recommendations,
  hasRecommendations,
}) => {
  return (
    <div className="flex-1">
      {hasRecommendations ? (
        <div className="bg-white shadow-2xl border-2 border-indigo-500 rounded-3xl p-6 h-80 flex items-center justify-center">
          <p className="text-center text-indigo-700 font-semibold text-lg">
            {recommendations}
          </p>
        </div>
      ) : (
        <div className="bg-white shadow-2xl border-2 border-indigo-500 rounded-3xl p-6 h-80 flex items-center justify-center">
          <p className="text-center text-indigo-500 font-semibold">
            No recommendations yet. Start your search above!
          </p>
        </div>
      )}
    </div>
  );
};

export default OutputSection;
